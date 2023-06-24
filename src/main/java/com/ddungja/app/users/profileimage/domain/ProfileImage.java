package com.ddungja.app.users.profileimage.domain;


import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ProfileImage {

    private final Long id;
    private final String url;
    private final LocalDateTime createDate;
    private final LocalDateTime updateDate;

    @Builder
    private ProfileImage(Long id, String url, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.url = url;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
}
