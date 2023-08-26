package com.ddungja.petmily.user.domain.profile;

import com.ddungja.petmily.user.domain.request.MyProfileCreateRequest;
import com.ddungja.petmily.user.domain.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class ProfileTest {


    @DisplayName("MyProfileCreateRequest와 ProfileImage, User로 프로필을 생성할 수 있다. ")
    @Test
    void from() {

        MyProfileCreateRequest myProfileCreateRequest = MyProfileCreateRequest.builder()
                .profileImageId(1L)
                .job("job")
                .environment("environment")
                .people(1)
                .comment("comment")
                .openTalk("openTalk")
                .region("region")
                .isExperience(false)
                .experiences(new ArrayList<>())
                .build();
        User user = User.builder()
                .id(1L)
                .build();
        ProfileImage profileImage = ProfileImage.builder()
                .id(1L)
                .url("url")
                .build();
        Profile profile = Profile.from(myProfileCreateRequest, profileImage, user);
        assertThat(profile.getJob()).isEqualTo("job");
    }

    @Test
    void update() {
    }

    @Test
    void deleteExperiences() {
    }
}