package com.ddungja.petmily.user.service;

import com.ddungja.petmily.user.domain.kakao.KakaoProfile;
import com.ddungja.petmily.user.domain.request.KaKaoLoginRequest;
import com.ddungja.petmily.user.repository.KakaoApiClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoService {

    private final KakaoApiClient client;

    @Value("${kakao.tokenUrl}")
    private String kakaoTokenUrl;

    @Value("${kakao.userInfoUrl}")
    private String kakaoUserInfoURl;

    @Value("${kakao.restApiKey}")
    private String restapiKey;

    @Value("${kakao.redirectUrl}")
    private String redirectUrl;

    public KakaoProfile getInfo(KaKaoLoginRequest kaKaoLoginRequest) throws URISyntaxException {
//        final KakaoToken token = getToken(code);
        return client.getInfo(new URI(kakaoUserInfoURl), kaKaoLoginRequest.getTokenType() + " " + kaKaoLoginRequest.getAccessToken());
    }
//    private KakaoToken getToken(final String code) throws URISyntaxException {
//        return client.getToken(new URI(kakaoTokenUrl), restapiKey, redirectUrl, code, "authorization_code");
//    }
}