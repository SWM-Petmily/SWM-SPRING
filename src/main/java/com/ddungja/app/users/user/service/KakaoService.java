package com.ddungja.app.users.user.service;

import com.ddungja.app.users.user.domain.KakaoProfile;
import com.ddungja.app.users.user.domain.KakaoToken;
import com.ddungja.app.users.user.repository.KakaoOpenFeign;
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

    private final KakaoOpenFeign kakao;

    @Value("${kakao.tokenUrl}")
    private String kakaoTokenUrl;

    @Value("${kakao.userInfoUrl}")
    private String kakaoUserInfoURl;

    @Value("${kakao.restApiKey}")
    private String restapiKey;

    @Value("${kakao.redirectUrl}")
    private String redirectUrl;

    public KakaoProfile getInfo(String tokenType, String accessToken) throws URISyntaxException {
        return kakao.getInfo(new URI(kakaoUserInfoURl), tokenType + " " + accessToken);
    }


}