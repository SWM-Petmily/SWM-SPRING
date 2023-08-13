package com.ddungja.petmily.user.controller.response;


import com.ddungja.petmily.user.domain.profile.ProfileImage;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProfileImageResponse {
    private final Long id;
    private final String url;
    @Builder
    public ProfileImageResponse(Long id, String url) {
        this.id = id;
        this.url = url;
    }
    public static ProfileImageResponse from(ProfileImage profileImage) {
        return ProfileImageResponse.builder()
                .id(profileImage.getId())
                .url(profileImage.getUrl())
                .build();
    }
}
