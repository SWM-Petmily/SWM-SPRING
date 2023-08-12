package com.ddungja.petmily.user.controller.port;

import com.ddungja.petmily.user.domain.kakao.KakaoProfile;
import com.ddungja.petmily.user.domain.request.KaKaoLoginRequest;

import java.net.URISyntaxException;

public interface KakaoService {
    KakaoProfile getInfo(KaKaoLoginRequest kaKaoLoginRequest) throws URISyntaxException;

}
