package com.dawull.book.springboot.config.auth;

import com.dawull.book.springboot.config.auth.dto.OAuthAttributes;
import com.dawull.book.springboot.config.auth.dto.SessionUser;
import com.dawull.book.springboot.domain.user.User;
import com.dawull.book.springboot.domain.user.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final HttpSession httpsession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // ~Id는 현재 로그인 진행 중인 서비스를 구분하는 코드
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        //OAuth2UserService를 통해 가져온 OAuth2User의 Attribute를 담을 클래스, 즉 DTO 역할
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        /* 디버깅 코드*/
        System.out.println("OAuth2 attributes = " + oAuth2User.getAttributes());
        System.out.println("Mapped attributes = " + attributes.getAttributes());
        System.out.println("nameAttributeKey = " + attributes.getNameAttributeKey());

        User user = saveOrUpdate(attributes);

        httpsession.setAttribute("user", new SessionUser(user));

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey()))
                , attributes.getAttributes(), attributes.getNameAttributeKey());
    }

    /* 구글 사용자정보가 업데이트 되었을 때를 대비하여 update 기능도 구현 */
    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture())).orElse(attributes.toEntity());
        return userRepository.save(user);

    }
}
