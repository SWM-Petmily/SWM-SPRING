package com.ddungja.petmily.user.domain.profile;

import com.ddungja.petmily.user.domain.request.ExperienceUpdateRequest;
import com.ddungja.petmily.user.domain.request.MyProfileCreateRequest;
import com.ddungja.petmily.user.domain.request.ProfileUpdateRequest;
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

    @DisplayName("ProfileUpdateRequest와 ProfileImage로 프로필을 수정할 수 있다.")
    @Test
    void update() {

        User user = User.builder()
                .id(1L)
                .build();
        ProfileImage profileImage = ProfileImage.builder()
                .id(1L)
                .url("url")
                .build();
        ProfileImage updateProfile = ProfileImage.builder()
                .id(2L)
                .url("url")
                .build();
        Profile profile = Profile.builder()
                .profileImage(profileImage)
                .people(1)
                .job("학생")
                .environment("아파트")
                .people(1)
                .comment("comment")
                .openTalk("openTalk")
                .region("region")
                .isExperience(false)
                .experiences(new ArrayList<>())
                .user(user)
                .build();
        ArrayList<ExperienceUpdateRequest> experiences = new ArrayList<>();
        experiences.add(ExperienceUpdateRequest.builder()
                .species("불독")
                .period(2)
                .build());
        experiences.add(ExperienceUpdateRequest.builder()
                .species("포메리안")
                .period(2)
                .build());
        ProfileUpdateRequest profileUpdateRequest = ProfileUpdateRequest.builder()
                .job("직장인")
                .environment("집")
                .people(2)
                .comment("comment2")
                .openTalk("naver.com")
                .region("서울")
                .isExperience(false)
                .experiences(experiences)
                .build();

        profile.update(profileUpdateRequest, updateProfile);

        assertThat(profile.getProfileImage().getId()).isEqualTo(updateProfile.getId());
        assertThat(profile.getJob()).isEqualTo("직장인");
        assertThat(profile.getEnvironment()).isEqualTo("집");
        assertThat(profile.getPeople()).isEqualTo(2);
        assertThat(profile.getComment()).isEqualTo("comment2");
        assertThat(profile.getOpenTalk()).isEqualTo("naver.com");
        assertThat(profile.getRegion()).isEqualTo("서울");
        assertThat(profile.isExperience()).isFalse();
        assertThat(profile.getExperiences()).hasSize(2);
    }
}