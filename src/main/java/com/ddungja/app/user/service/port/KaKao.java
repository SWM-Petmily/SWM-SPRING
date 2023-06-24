package com.ddungja.app.user.service.port;

import com.ddungja.app.user.infrastructure.KakaoProfile;
import com.ddungja.app.user.infrastructure.KakaoToken;

import java.net.URI;

public interface KaKao {

    KakaoProfile getInfo(URI baseUrl, String accessToken);

    KakaoToken getToken(URI baseUrl, String restApiKey, String redirectUrl, String code, String grantType);
}
