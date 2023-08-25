package com.ddungja.petmily.user.service;

import com.ddungja.petmily.mock.TestContainer;
import com.ddungja.petmily.user.domain.certification.Certification;
import com.ddungja.petmily.user.domain.kakao.KakaoProfile;
import com.ddungja.petmily.user.domain.profile.ProfileImage;
import com.ddungja.petmily.user.domain.request.UserCreateRequest;
import com.ddungja.petmily.user.domain.request.UserUpdateRequest;
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
        testContainer.userService.signUp(user.getId(), userCreateRequest);
        User sut = testContainer.userRepository.findById(user.getId()).get();

        //then
        assertThat(sut.isCertification()).isTrue();
        assertThat(sut.getNickname()).isEqualTo("닉네임");
        assertThat(sut.getProfileImage()).isEqualTo(profileImage);
        assertThat(sut.getPhone()).isEqualTo("010-1234-5678");
    }

    @DisplayName("유저는 프로필 사진과 닉네임을 변경할 수 있다.")
    @Test
    void modify() {
        //given
        TestContainer testContainer = TestContainer.builder().build();
        ProfileImage profileImage1 = testContainer.profileImageRepository.save(ProfileImage.builder()
                .url("https://test.com")
                .build());

        ProfileImage profileImage2 = testContainer.profileImageRepository.save(ProfileImage.builder()
                .url("https://test.com")
                .build());

        User user = testContainer.userRepository.save(User.builder()
                .nickname("닉네임")
                .profileImage(profileImage1)
                .build());
        UserUpdateRequest userUpdateRequest = UserUpdateRequest.builder()
                .nickname("수정된 닉네임")
                .profileImageId(profileImage2.getId())
                .build();

        //when
        testContainer.userService.modify(user.getId(), userUpdateRequest);
        User sut = testContainer.userRepository.findById(user.getId()).get();

        //then
        assertThat(sut.getNickname()).isEqualTo("수정된 닉네임");
        assertThat(sut.getProfileImage()).isEqualTo(profileImage2);


    }

    @Test
    void appleLogin() {
        //given
        //when
        //then
    }

    @Test
     void getMyPage(){
        //given
        //when
        //then
    }


}