package com.ddungja.app.users.interest.domain;


import com.ddungja.app.posts.post.infrastructure.PostEntity;
import com.ddungja.app.users.user.infrastructure.entity.UserEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Interest {
    private final Long id;
    private final UserEntity user;
    private final PostEntity post;
    private final LocalDateTime createDate;
    private final LocalDateTime updateDate;


    @Builder
    private Interest(Long id, UserEntity user, PostEntity post, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.user = user;
        this.post = post;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
}
