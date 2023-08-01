package com.ddungja.petmily.user.controller.response;

import com.ddungja.petmily.user.domain.profile.Profile;
import lombok.Builder;
import lombok.Getter;


@Getter
public class MyProfileCreateResponse {
    private final Long profileId;
    @Builder
    private MyProfileCreateResponse(Long profileId) {
        this.profileId = profileId;
    }

    public static MyProfileCreateResponse from(Profile profile) {
        return MyProfileCreateResponse.builder()
                .profileId(profile.getId())
                .build();
    }
}
