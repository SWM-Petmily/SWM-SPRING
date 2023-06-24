package com.ddungja.app.user.infrastructure;


import com.ddungja.app.user.domain.KakaoProfile;
import com.ddungja.app.user.domain.KakaoToken;
import com.ddungja.app.user.service.port.KaKaoApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URI;

@RequiredArgsConstructor
@Component
public class KakaoApiImpl implements KaKaoApi {

    private final KakaoOpenFeign kakaoOpenFeign;

    @Override
    public KakaoProfile getInfo(URI baseUrl, String accessToken) {
        return kakaoOpenFeign.getInfo(baseUrl, accessToken);
    }

    @Override
    public KakaoToken getToken(URI baseUrl, String restApiKey, String redirectUrl, String code, String grantType) {
        return kakaoOpenFeign.getToken(baseUrl, restApiKey, redirectUrl, code, grantType);
    }
}
