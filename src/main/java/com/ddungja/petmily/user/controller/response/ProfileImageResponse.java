package com.ddungja.petmily.user.controller.response;


import com.ddungja.petmily.user.domain.ProfileImage;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProfileImageResponse {
    private final String url;


    @Builder
    public ProfileImageResponse(String url) {
        this.url = url;
    }

    public static ProfileImageResponse from(ProfileImage profileImage) {
        return ProfileImageResponse.builder()
                .url(profileImage.getUrl())
                .build();
    }
}
