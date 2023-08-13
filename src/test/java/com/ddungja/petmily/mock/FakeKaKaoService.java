package com.ddungja.petmily.mock;


import com.ddungja.petmily.user.controller.port.KakaoService;
import com.ddungja.petmily.user.domain.kakao.KakaoProfile;
import com.ddungja.petmily.user.domain.request.KaKaoLoginRequest;

import java.net.URISyntaxException;

public class FakeKaKaoService implements KakaoService {
    @Override
    public KakaoProfile getInfo(KaKaoLoginRequest kaKaoLoginRequest) throws URISyntaxException {
        KakaoProfile.KakaoAccount kakaoAccount = KakaoProfile.KakaoAccount.builder()
                .email("test@naver.com")
                .build();

        return KakaoProfile.builder()
                .kakao_account(kakaoAccount)
                .build();
    }


}
