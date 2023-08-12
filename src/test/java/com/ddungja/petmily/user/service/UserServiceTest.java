package com.ddungja.petmily.user.service;

import com.ddungja.petmily.mock.TestContainer;
import com.ddungja.petmily.user.domain.certification.Certification;
import com.ddungja.petmily.user.domain.kakao.KakaoProfile;
import com.ddungja.petmily.user.domain.profile.ProfileImage;
import com.ddungja.petmily.user.domain.request.UserCreateRequest;
import com.ddungja.petmily.user.domain.user.ProviderType;
import com.ddungja.petmily.user.domain.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserServiceTest {

    @DisplayName("유저는 kakaoProfile로 회원가입 할 수 있다.")
    @Test
    void kakagoLogin() {
        TestContainer testContainer = TestContainer.builder().build();
        KakaoProfile.KakaoAccount kakaoAccount = KakaoProfile.KakaoAccount.builder()
                .email("test@naver.com")
                .build();

        KakaoProfile kakaoProfile = KakaoProfile.builder()
                .kakao_account(kakaoAccount)
                .build();


        User user = testContainer.userService.kakagoLogin(kakaoProfile);
        assertThat(user.getProvider()).isEqualTo(ProviderType.KAKAO);
        assertThat(user.getEmail()).isEqualTo("test@naver.com");
        assertThat(user.isProfile()).isFalse();
        assertThat(user.isCertification()).isFalse();
        assertThat(user.getPhone()).isNull();
        assertThat(user.getNickname()).isNull();
    }

    @DisplayName("유저는 회원가입할 수 있다.")
    @Test
    void signUp() {
        //given
        TestContainer testContainer = TestContainer.builder().build();
        User user = testContainer.userRepository.save(User.builder()
                .email("test@naver.com")
                .isCertification(false)
                .build());
        ProfileImage profileImage = testContainer.profileImageRepository.save(ProfileImage.builder()
                .url("https://test.com")
                .build());

        testContainer.certificationRepository.save(
                Certification.builder()
                        .phoneNumber("010-1234-5678")
                        .isCertification(true)
                        .user(user)
                        .build());

        UserCreateRequest userCreateRequest = UserCreateRequest.builder()
                .nickname("닉네임")
                .build();

        //when
        testContainer.userService.signUp(1L, userCreateRequest);
        User sut = testContainer.userRepository.findById(1L).get();

        //then
        assertThat(sut.isCertification()).isTrue();
        assertThat(sut.getNickname()).isEqualTo("닉네임");
        assertThat(sut.getProfileImage()).isEqualTo(profileImage);
        assertThat(sut.getPhone()).isEqualTo("010-1234-5678");
    }

    @Test
    void modifyNickname() {
    }

    @Test
    void appleLogin() {
    }

    @Test
    void reset() {
    }
}