package com.ddungja.petmily.user.domain.user;

import com.ddungja.petmily.common.exception.CustomException;
import com.ddungja.petmily.user.domain.certification.Certification;
import com.ddungja.petmily.user.domain.profile.ProfileImage;
import com.ddungja.petmily.user.domain.request.UserCreateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserTest {

    @DisplayName("유저는 UserCreateRequest와 Certification으로 회원가입을하고 phone과 nickname을 저장한다.")
    @Test
    void signUp() {
        //given
        User user = User.builder()
                .isCertification(false)
                .build();

        Certification certification = Certification.builder()
                .id(1L)
                .certificationNumber("123456")
                .isCertification(true)
                .phoneNumber("010-1234-5678")
                .build();

        UserCreateRequest userCreateRequest = UserCreateRequest.builder()
                .nickname("닉네임")
                .build();

        ProfileImage profileImage = ProfileImage
                .builder()
                .build();

        //when
        user.signUp(userCreateRequest, certification, profileImage);

        //then
        assertThat(user.isCertification()).isTrue();
        assertThat(user.getPhone()).isEqualTo("010-1234-5678");
        assertThat(user.getNickname()).isEqualTo("닉네임");
        assertThat(user.getProfileImage()).isNotNull();
    }


    @DisplayName("이미 인증받은 유저가 회원가입을 하면 에러를 던진다.")
    @Test
    void signUpFail_USER_ALREADY_CERTIFICATION() {
        //given
        User user = User.builder()
                .isCertification(true)
                .build();

        Certification certification = Certification.builder()
                .id(1L)
                .certificationNumber("123456")
                .isCertification(true)
                .phoneNumber("010-1234-5678")
                .build();

        UserCreateRequest userCreateRequest = UserCreateRequest.builder()
                .nickname("닉네임")
                .build();

        ProfileImage profileImage = ProfileImage
                .builder()
                .build();

        //when
        assertThatThrownBy(() ->
                user.signUp(userCreateRequest, certification, profileImage)
        ).isInstanceOf(CustomException.class);

    }


    @DisplayName("휴대전화 인증번호를 인증하지 않은 유저라면 예외를 발생시킨다.")
    @Test
    void signUpFail_CERTIFICATION_NOT_COMPLETE() {
        //given
        User user = User.builder()
                .isCertification(false)
                .build();

        Certification certification = Certification.builder()
                .id(1L)
                .certificationNumber("123456")
                .isCertification(false)
                .phoneNumber("010-1234-5678")
                .build();

        UserCreateRequest userCreateRequest = UserCreateRequest.builder()
                .nickname("닉네임")
                .build();

        ProfileImage profileImage = ProfileImage
                .builder()
                .build();

        //when
        assertThatThrownBy(() ->
                user.signUp(userCreateRequest, certification, profileImage)
        ).isInstanceOf(CustomException.class);

    }

    @DisplayName("유저는 nickname과 profileImage를 수정할 수 있다.")
    @Test
    void update() {
        //given
        ProfileImage profileImage = ProfileImage.builder()
                .url("https://encrypted-tbn1.gstatic.com/licensed-image?q=tbn:ANd9GcREj22c-wMNL5IDmU99v8G7voUl17Yxm0JJqMLqttdPT4DnaB99zqVK7HWiNzjP3aZnzCEf-ikAqb2yiDk")
                .build();

        ProfileImage profileImageUpdate = ProfileImage.builder()
                .url("https://encrypted-tbn1.gstatic.com/licensed-image?q=tbn:ANd9GcREj22c-wMNL5IDmU99v8G7voUl17Yxm0JJqMLqttdPT4DnaB99zqVK7HWiNzjP3aZnzCEf-ikAqb2yiDk")
                .build();

        User user = User.builder()
                .nickname("변경 전 닉네임")
                .profileImage(profileImage)
                .build();

        String nickname = "변경 후 닉네임";

        //when
        user.update(nickname, profileImageUpdate);

        //then
        assertThat(user.getNickname()).isEqualTo(nickname);
        assertThat(user.getProfileImage()).isEqualTo(profileImageUpdate);
    }


    @DisplayName("유저는 프로필을 생성할 수 있다.")
    @Test
    void createProfile() {
        //given
        User user = User.builder()
                .isProfile(false)
                .build();

        //when
        user.createProfile();

        //then
        assertThat(user.isProfile()).isTrue();
    }
}