package com.dawull.book.springboot.config.auth;

import com.dawull.book.springboot.domain.user.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


//@RequiredArgsConstructor
@Configuration
@EnableWebSecurity      // spring security 설정들 활성화
public class SecurityConfig {
    private final CustomOAuth2UserService customOAuth2UserService;

    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService) {
        this.customOAuth2UserService = customOAuth2UserService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // CSRF / H2 콘솔 설정
                .csrf(csrf -> {
                    try {
                        csrf.disable()
                                .headers(headers -> headers.frameOptions(frame -> frame.disable()))
                                // 인가 설정
                                .authorizeHttpRequests(auth -> auth
                                        .requestMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**")
                                        .permitAll()
                                        .requestMatchers("/api/v1/**").hasRole(Role.USER.name())
                                        .anyRequest().authenticated()
                                )
                                // 로그아웃 설정
                                .logout(logout -> logout
                                        .logoutSuccessUrl("/")
                                )
                                // OAuth2 로그인 설정
                                .oauth2Login(oauth2 -> oauth2
                                        //userInfoEndpoint: 로그인성공 후 사용자정보 가져오기 설정
                                        .userInfoEndpoint(userInfo -> userInfo
                                                .userService(customOAuth2UserService)
                                        ));
                    } catch (Exception e) {

                    }
                });

        return http.build();
    }

}
