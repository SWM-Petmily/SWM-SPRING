package com.ddungja.petmily.user.controller.response;

import com.ddungja.petmily.user.domain.profile.Profile;
import lombok.Builder;
import lombok.Getter;


@Getter
public class MyProfileUpdateResponse {
    private final Long profileId;
    @Builder
    private MyProfileUpdateResponse(Long profileId) {
        this.profileId = profileId;
    }
    public static MyProfileUpdateResponse from(Profile profile) {
        return MyProfileUpdateResponse.builder()
                .profileId(profile.getId())
                .build();
    }
}
