package com.ddungja.app.users.user.domain;


import com.ddungja.app.users.user.domain.Experience;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ProfileCreateRequest {
    private final Long profileImageId;
    private final String job;
    private final String environment;
    private final int people;
    private final String comment;
    private final String color;
    private final String openTalk;
    private final String region;
    private final boolean isExperience;
    private final List<Experience> experiences;

}
