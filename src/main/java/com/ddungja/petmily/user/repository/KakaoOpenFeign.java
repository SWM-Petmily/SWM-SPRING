package com.ddungja.petmily.user.repository;

import com.ddungja.petmily.global.config.OpenFeignConfig;
import com.ddungja.petmily.user.domain.KakaoProfile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.net.URI;

@FeignClient(name = "kakaoClient", configuration = OpenFeignConfig.class)
public interface KakaoOpenFeign {

    @PostMapping
    KakaoProfile getInfo(URI baseUrl, @RequestHeader("Authorization") String accessToken);



}