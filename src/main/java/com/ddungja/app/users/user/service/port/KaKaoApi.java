package com.ddungja.app.users.user.service.port;

import com.ddungja.app.users.user.domain.KakaoProfile;
import com.ddungja.app.users.user.domain.KakaoToken;

import java.net.URI;

public interface KaKaoApi {

    KakaoProfile getInfo(URI baseUrl, String accessToken);

    KakaoToken getToken(URI baseUrl, String restApiKey, String redirectUrl, String code, String grantType);
}
