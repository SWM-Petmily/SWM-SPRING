package com.ddungja.petmily.user.service;

import com.ddungja.petmily.user.controller.port.KakaoService;
import com.ddungja.petmily.user.domain.kakao.KakaoProfile;
import com.ddungja.petmily.user.domain.request.KaKaoLoginRequest;
import com.ddungja.petmily.user.repository.KakaoApiClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@Service

public class KakaoServiceImpl implements KakaoService {

    private final KakaoApiClient client;
    private final String kakaoTokenUrl;
    private final String kakaoUserInfoURl;
    private final String restapiKey;
    private final String redirectUrl;


    public KakaoServiceImpl(KakaoApiClient client
            , @Value("${kakao.tokenUrl}") String kakaoTokenUrl
            , @Value("${kakao.userInfoUrl}") String kakaoUserInfoURl
            , @Value("${kakao.restApiKey}") String restapiKey
            , @Value("${kakao.redirectUrl}") String redirectUrl) {
        this.client = client;
        this.kakaoTokenUrl = kakaoTokenUrl;
        this.kakaoUserInfoURl = kakaoUserInfoURl;
        this.restapiKey = restapiKey;
        this.redirectUrl = redirectUrl;
    }

    public KakaoProfile getInfo(KaKaoLoginRequest kaKaoLoginRequest) throws URISyntaxException {
        return client.getInfo(new URI(kakaoUserInfoURl), kaKaoLoginRequest.getTokenType() + " " + kaKaoLoginRequest.getAccessToken());
    }
}