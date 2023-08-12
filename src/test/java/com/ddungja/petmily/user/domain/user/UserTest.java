package com.ddungja.petmily.user.domain.user;

import com.ddungja.petmily.user.domain.certification.Certification;
import com.ddungja.petmily.user.domain.profile.ProfileImage;
import com.ddungja.petmily.user.domain.request.UserCreateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @DisplayName("유저는 UserCreateRequest와 Certification으로 회원가입을하고 phone과 nickname을 저장한다.")
    @Test
    void signUp() {
        //given
        User user = User.builder()
                .build();

        Certification certification = Certification.builder()
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