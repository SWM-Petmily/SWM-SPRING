package com.ddungja.petmily.user.controller.response;

import com.ddungja.petmily.user.domain.profile.Profile;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ProfileResponse {
    private final String job;
    private final String environment;
    private final int people;
    private final String comment;
    private final String openTalk;
    private final String region;
    private final Boolean isExperience;
    private final ProfileUserResponse user;
    private final ProfileImageResponse profileImage;
    private final List<ProfileExperienceResponse> experiences;
    private final Boolean isMyProfile;
    @Builder
    private ProfileResponse(String job, String environment, int people, String comment, String openTalk, String region, Boolean isExperience, ProfileUserResponse user, ProfileImageResponse profileImage, List<ProfileExperienceResponse> experiences, Boolean isMyProfile) {
        this.job = job;
        this.environment = environment;
        this.people = people;
        this.comment = comment;
        this.openTalk = openTalk;
        this.region = region;
        this.isExperience = isExperience;
        this.user = user;
        this.profileImage = profileImage;
        this.experiences = experiences;
        this.isMyProfile = isMyProfile;
    }
    public static ProfileResponse from(Profile profile, boolean isMyProfile) {
        return ProfileResponse.builder()
                .job(profile.getJob())
                .environment(profile.getEnvironment())
                .people(profile.getPeople())
                .comment(profile.getComment())
                .openTalk(profile.getOpenTalk())
                .region(profile.getRegion())
                .isExperience(profile.isExperience())
                .user(ProfileUserResponse.from(profile.getUser()))
                .profileImage(ProfileImageResponse.from(profile.getProfileImage()))
                .experiences(profile.getExperiences().stream().map(ProfileExperienceResponse::from).toList())
                .isMyProfile(isMyProfile)
                .build();
    }
}


