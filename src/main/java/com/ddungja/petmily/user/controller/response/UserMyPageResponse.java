package com.ddungja.petmily.user.controller.response;

import com.ddungja.petmily.user.domain.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserMyPageResponse {
    private final Long userId;
    private final String nickname;
    private final Boolean isProfile;
    private final int likeCount;
    private final int applyCount;
    private final String url;

    @Builder
    private UserMyPageResponse(Long userId, String nickname, Boolean isProfile, int likeCount, int applyCount, String url) {
        this.userId = userId;
        this.nickname = nickname;
        this.isProfile = isProfile;
        this.likeCount = likeCount;
        this.applyCount = applyCount;
        this.url = url;
    }

    public static UserMyPageResponse from(User user, int applyCount, int likeCount) {
        return UserMyPageResponse.builder()
                .userId(user.getId())
                .nickname(user.getNickname())
                .isProfile(user.isProfile())
                .url(user.getProfileImage().getUrl())
                .applyCount(applyCount)
                .likeCount(likeCount)
                .build();
    }
}
