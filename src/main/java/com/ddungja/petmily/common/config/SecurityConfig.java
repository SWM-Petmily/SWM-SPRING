package com.ddungja.petmily.common.config;

//import com.ddungja.petmily.common.jwt.JwtAuthorizationFilter;

import com.ddungja.petmily.common.jwt.JwtAuthorizationFilter;
import com.ddungja.petmily.common.jwt.JwtProvider;
import com.ddungja.petmily.common.response.SecurityResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {
    private final JwtProvider jwtProvider;
    private static final String[] AUTHENTICATION_WHITELIST = {
            "/",
            "/swagger-ui/**",
            "/api-docs/**",
            "/api-docs",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/api/**",
            "/h2-console",
            "/h2-console/**",
            "/users/kakao",
            "/users/apple",
            "/users/refresh",
            "/users/test/**",
            "/users/profile/{userId}",
            "/send-one",
            "/category/**",
            "/post/main",
            "/test/**",
            "/open-api/**",
    };

    @RequiredArgsConstructor
    public static class CustomSecurityFilterManager extends AbstractHttpConfigurer<CustomSecurityFilterManager, HttpSecurity> {
        private final JwtProvider jwtProvider;

        @Override
        public void configure(HttpSecurity http) {
            AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
            http.addFilter(new JwtAuthorizationFilter(authenticationManager, jwtProvider));
        }
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.headers(httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
        http.sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.httpBasic(AbstractHttpConfigurer::disable);
        http.formLogin(AbstractHttpConfigurer::disable);
        http.csrf(AbstractHttpConfigurer::disable);
        http.headers(httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
        http.apply(new CustomSecurityFilterManager(jwtProvider));
        http.exceptionHandling(configurer -> configurer.authenticationEntryPoint((request, response, accessDeniedException) -> SecurityResponse.unAuthentication(response)));
        http.exceptionHandling(configurer -> configurer.accessDeniedHandler((request, response, accessDeniedException) -> SecurityResponse.forbidden(response)));
//        http.authorizeHttpRequests(authorize -> authorize.requestMatchers(AUTHENTICATION_WHITELIST).permitAll());
//        http.authorizeHttpRequests(authorize -> authorize.requestMatchers(PathRequest.toH2Console()).permitAll());
        http.authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll());
//        http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/authorization").authenticated());
//        http.authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll());
        return http.build();
    }
}
