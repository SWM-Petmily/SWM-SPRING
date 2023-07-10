package com.ddungja.petmily.user.controller.response;

import com.ddungja.petmily.user.domain.Profile;
import lombok.Builder;
import lombok.Getter;


@Getter
public class ProfileCreateResponse {
    private final Long profileId;
    @Builder
    private ProfileCreateResponse(Long profileId) {
        this.profileId = profileId;
    }

    public static ProfileCreateResponse from(Profile profile) {
        return ProfileCreateResponse.builder()
                .profileId(profile.getId())
                .build();
    }
}
