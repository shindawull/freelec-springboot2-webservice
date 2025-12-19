package com.dawull.book.springboot.config.auth.dto;

import com.dawull.book.springboot.domain.user.Role;
import com.dawull.book.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    // OAuth2User에서 반환하는 사용자정보를 변환(사용자정보=Map)
    public static OAuthAttributes of(String registrationId,
                                     String userNameAttributeName,
                                     Map<String, Object> attributes) {
        if ("google".equals(registrationId)) { //수정
            return ofGoogle(userNameAttributeName, attributes);
        }
        throw new IllegalArgumentException("Unsupported OAuth2 provider: " + registrationId); // 수정
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName,
                                            Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes) // 수정
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    /* 생성시점은 가입할 때
     * 가입할 때 기본 권한은 GUEST -> role 빌더값인 Role.GUEST 사용
     * OAuthAttributes클래스 생성 끝났으면 같은패키지에 SessionUser클래스 생성*/
    public User toEntity() {
        return User.builder()
                .name(name).email(email).picture(picture).role(Role.GUEST)
                .build();
    }
}
