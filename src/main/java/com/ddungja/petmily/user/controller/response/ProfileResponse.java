package com.ddungja.petmily.user.controller.response;

import com.ddungja.petmily.user.domain.Profile;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ProfileResponse {
    private final String job;
    private final String environment;
    private final int people;
    private final String comment;
    private final String color;
    private final String openTalk;
    private final String region;
    private final Boolean isExperience;
    private final UserResponse user;
    private final ProfileImageResponse profileImage;
    private final List<ExperienceResponse> experiences;
    private final Boolean isMyProfile;


    @Builder
    public ProfileResponse(String job, String environment, int people, String comment, String color, String openTalk, String region, Boolean isExperience, UserResponse user, ProfileImageResponse profileImage, List<ExperienceResponse> experiences, Boolean isMyProfile) {
        this.job = job;
        this.environment = environment;
        this.people = people;
        this.comment = comment;
        this.color = color;
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
                .user(UserResponse.from(profile.getUser()))
                .profileImage(ProfileImageResponse.from(profile.getProfileImage()))
                .experiences(profile.getExperiences().stream().map(ExperienceResponse::from).toList())
                .isMyProfile(isMyProfile)
                .build();
    }
}


