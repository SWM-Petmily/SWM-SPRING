package com.ddungja.petmily.user.controller.response;

import com.ddungja.petmily.user.domain.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserMyPageResponse {
    private final String nickname;
    private final Boolean isProfile;
    private final int likeCount;
    private final int applyCount;
    private final String imageUrl;

    @Builder
    private UserMyPageResponse(String nickname, Boolean isProfile, int likeCount, int applyCount, String url) {
        this.nickname = nickname;
        this.isProfile = isProfile;
        this.likeCount = likeCount;
        this.applyCount = applyCount;
        this.imageUrl = url;
    }

    public static UserMyPageResponse from(User user, int applyCount, int likeCount) {
        return UserMyPageResponse.builder()
                .nickname(user.getNickname())
                .isProfile(user.isProfile())
                .url(user.getProfileImage().getUrl())
                .applyCount(applyCount)
                .likeCount(likeCount)
                .build();
    }
}
