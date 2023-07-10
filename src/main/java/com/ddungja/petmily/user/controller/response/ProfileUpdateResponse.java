package com.ddungja.petmily.user.controller.response;

import com.ddungja.petmily.user.domain.Profile;
import lombok.Builder;
import lombok.Getter;


@Getter
public class ProfileUpdateResponse {
    private final Long profileId;
    @Builder
    private ProfileUpdateResponse(Long profileId) {
        this.profileId = profileId;
    }
    public static ProfileUpdateResponse from(Profile profile) {
        return ProfileUpdateResponse.builder()
                .profileId(profile.getId())
                .build();
    }
}
