package com.ddungja.app.user.infrastructure;


import com.ddungja.app.user.service.port.KaKao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URI;

@RequiredArgsConstructor
@Component
public class KakaoImpl implements KaKao {

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
