package com.ddungja.petmily.user.repository;

import com.ddungja.petmily.common.config.OpenFeignConfig;
import com.ddungja.petmily.user.domain.kakao.KakaoProfile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.net.URI;

@FeignClient(name = "kakaoClient", configuration = OpenFeignConfig.class)
public interface KakaoApiClient {
    @PostMapping
    KakaoProfile getInfo(URI baseUrl, @RequestHeader("Authorization") String accessToken);
}