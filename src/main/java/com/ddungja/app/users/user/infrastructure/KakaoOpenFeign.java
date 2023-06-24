package com.ddungja.app.users.user.infrastructure;

import com.ddungja.app.global.config.OpenFeignConfig;
import com.ddungja.app.users.user.domain.KakaoProfile;
import com.ddungja.app.users.user.domain.KakaoToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;

@FeignClient(name = "kakaoClient", configuration = OpenFeignConfig.class)
public interface KakaoOpenFeign {

    @PostMapping
    KakaoProfile getInfo(URI baseUrl, @RequestHeader("Authorization") String accessToken);

    @PostMapping
    KakaoToken getToken(URI baseUrl, @RequestParam("client_id") String restApiKey,
                        @RequestParam("redirect_uri") String redirectUrl,
                        @RequestParam("code") String code,
                        @RequestParam("grant_type") String grantType);

}