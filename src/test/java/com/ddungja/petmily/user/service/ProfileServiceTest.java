package com.ddungja.petmily.user.service;

import com.ddungja.petmily.common.exception.CustomException;
import com.ddungja.petmily.mock.TestContainer;
import com.ddungja.petmily.user.domain.profile.Profile;
import com.ddungja.petmily.user.domain.profile.ProfileImage;
import com.ddungja.petmily.user.domain.request.MyProfileCreateRequest;
import com.ddungja.petmily.user.domain.request.ProfileUpdateRequest;
import com.ddungja.petmily.user.domain.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ProfileServiceTest {

    @DisplayName("프로필을 가져올 수 있다.")
    @Test
    void get() {
        TestContainer testContainer = TestContainer.builder().build();
        User user = testContainer.userRepository.save(User.builder()
                .isProfile(true)
                .build());
        testContainer.profileRepository.save(Profile.builder()
                .user(user)
                .build());

        Profile profile = testContainer.profileService.get(user.getId());

        assertThat(profile.getUser().getId()).isEqualTo(user.getId());
    }

    @DisplayName("프로필이 없다면 에러를 던진다.")
    @Test
    void getFail_PROFILE_NOT_FOUND() {
        TestContainer testContainer = TestContainer.builder().build();
        User user = testContainer.userRepository.save(User.builder()
                .isProfile(false)
                .build());

        assertThatThrownBy(() ->
                testContainer.profileService.get(user.getId())
        ).isInstanceOf(CustomException.class);
    }


    @DisplayName("MyProfileCreateRequest로 유저는 프로필을 생성한다.")
    @Test
    void create() {
        TestContainer testContainer = TestContainer.builder().build();
        User user = testContainer.userRepository.save(User.builder()
                .isProfile(false)
                .build());

        ProfileImage profileImage = testContainer.profileImageRepository.save(ProfileImage.builder()
                .url("url")
                .build());

        MyProfileCreateRequest myProfileCreateRequest = MyProfileCreateRequest.builder()
                .profileImageId(profileImage.getId())
                .job("job")
                .environment("environment")
                .people(1)
                .comment("comment")
                .openTalk("openTalk")
                .region("region")
                .isExperience(false)
                .build();

        Profile profile = testContainer.profileService.create(myProfileCreateRequest, user.getId());

        assertThat(profile.getProfileImage().getId()).isEqualTo(profileImage.getId());
        ;
        assertThat(profile.getJob()).isEqualTo("job");
        assertThat(profile.getEnvironment()).isEqualTo("environment");
        assertThat(profile.getPeople()).isEqualTo(1);
        assertThat(profile.getComment()).isEqualTo("comment");
        assertThat(profile.getOpenTalk()).isEqualTo("openTalk");
        assertThat(profile.getRegion()).isEqualTo("region");
        assertThat(profile.isExperience()).isFalse();
    }

    @DisplayName("프로필을 생성할 프로필 이미지가 없다면 에러를 던진다.")
    @Test
    void createFail_PROFILE_IMAGE_NOT_FOUND() {
        TestContainer testContainer = TestContainer.builder().build();
        MyProfileCreateRequest myProfileCreateRequest = MyProfileCreateRequest.builder()
                .profileImageId(1L)
                .job("job")
                .environment("environment")
                .people(1)
                .comment("comment")
                .openTalk("openTalk")
                .region("region")
                .isExperience(false)
                .build();

        assertThatThrownBy(() ->
                testContainer.profileService.create(myProfileCreateRequest, 1L)
        ).isInstanceOf(CustomException.class);
    }

    @DisplayName("프로필을 생성할 때 유저가 없으면 에러를 던진다.")
    @Test
    void createFail_USER_NOT_FOUND() {
        TestContainer testContainer = TestContainer.builder().build();
        ProfileImage profileImage = testContainer.profileImageRepository.save(ProfileImage.builder()
                .url("url")
                .build());

        MyProfileCreateRequest myProfileCreateRequest = MyProfileCreateRequest.builder()
                .profileImageId(profileImage.getId())
                .job("job")
                .environment("environment")
                .people(1)
                .comment("comment")
                .openTalk("openTalk")
                .region("region")
                .isExperience(false)
                .build();

        assertThatThrownBy(() ->
                testContainer.profileService.create(myProfileCreateRequest, 1L)
        ).isInstanceOf(CustomException.class);
    }

    @DisplayName("프로필이 이미 존재하면 에러를 던진다.")
    @Test
    void createFail_PROFILE_ALREADY_EXISTS() {
        TestContainer testContainer = TestContainer.builder().build();

        User user = testContainer.userRepository.save(User.builder()
                .isProfile(true)
                .build());

        ProfileImage profileImage = testContainer.profileImageRepository.save(ProfileImage.builder()
                .url("url")
                .build());

        MyProfileCreateRequest myProfileCreateRequest = MyProfileCreateRequest.builder()
                .profileImageId(profileImage.getId())
                .job("job")
                .environment("environment")
                .people(1)
                .comment("comment")
                .openTalk("openTalk")
                .region("region")
                .isExperience(false)
                .build();

        assertThatThrownBy(() ->
                testContainer.profileService.create(myProfileCreateRequest, user.getId())
        ).isInstanceOf(CustomException.class);

    }

    @DisplayName("ProfileUpdateRequest로 유저는 프로필을 수정할 수 있다.")
    @Test
    void modify() {
        TestContainer testContainer = TestContainer.builder().build();
        User user = testContainer.userRepository.save(User.builder()
                .isProfile(true)
                .build());

        ProfileImage profileImage = testContainer.profileImageRepository.save(ProfileImage.builder()
                .url("url")
                .build());

        Profile profile = testContainer.profileRepository.save(Profile.builder()
                .profileImage(profileImage)
                .experiences(new ArrayList<>())
                .user(user)
                .build());

        ProfileUpdateRequest profileUpdateRequest = ProfileUpdateRequest.builder()
                .profileImageId(profileImage.getId())
                .job("job")
                .environment("environment")
                .people(1)
                .comment("comment")
                .openTalk("openTalk")
                .region("region")
                .isExperience(false)
                .experiences(new ArrayList<>())
                .build();

        testContainer.profileService.modify(profileUpdateRequest, user.getId());

        assertThat(profile.getProfileImage().getId()).isEqualTo(profileImage.getId());
        assertThat(profile.getJob()).isEqualTo("job");
        assertThat(profile.getEnvironment()).isEqualTo("environment");
        assertThat(profile.getPeople()).isEqualTo(1);
        assertThat(profile.getComment()).isEqualTo("comment");
        assertThat(profile.getOpenTalk()).isEqualTo("openTalk");
        assertThat(profile.getRegion()).isEqualTo("region");
        assertThat(profile.isExperience()).isFalse();
        assertThat(profile.getExperiences()).isEmpty();
    }


    @DisplayName("프로필을 수정할 때 프로필이 존재하지 않는다면 에러를 던진다.")
    @Test
    void modifyFail_PROFILE_NOT_FOUND() {
        TestContainer testContainer = TestContainer.builder().build();
        User user = testContainer.userRepository.save(User.builder()
                .isProfile(false)
                .build());

        ProfileImage profileImage = testContainer.profileImageRepository.save(ProfileImage.builder()
                .url("url")
                .build());

        ProfileUpdateRequest profileUpdateRequest = ProfileUpdateRequest.builder()
                .profileImageId(profileImage.getId())
                .job("job")
                .environment("environment")
                .people(1)
                .comment("comment")
                .openTalk("openTalk")
                .region("region")
                .isExperience(false)
                .experiences(new ArrayList<>())
                .build();

        assertThatThrownBy(() -> {
            testContainer.profileService.modify(profileUpdateRequest, user.getId());
        }).isInstanceOf(CustomException.class);
    }


    @DisplayName("ProfileUpdateRequest로 유저는 프로필을 수정할 수 있다.")
    @Test
    void modifyFail_PROFILE_IMAGE_NOT_FOUND() {
        TestContainer testContainer = TestContainer.builder().build();
        User user = testContainer.userRepository.save(User.builder()
                .isProfile(true)
                .build());


        Profile profile = testContainer.profileRepository.save(Profile.builder()
                .experiences(new ArrayList<>())
                .user(user)
                .build());

        ProfileUpdateRequest profileUpdateRequest = ProfileUpdateRequest.builder()
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

        assertThatThrownBy(() -> {
            testContainer.profileService.modify(profileUpdateRequest, user.getId());
        }).isInstanceOf(CustomException.class);
    }
}