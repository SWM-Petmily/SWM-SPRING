package com.ddungja.app.users.apply.domain;

import com.ddungja.app.post.infrastructure.entity.PostEntity;
import com.ddungja.app.users.user.infrastructure.entity.UserEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class Apply {

    private final Long id;
    private final UserEntity user;
    private final UserEntity seller;
    private final PostEntity post;
    private final Approval approval;
    private final LocalDateTime createDate;
    private final LocalDateTime updateDate;

    @Builder
    private Apply(Long id, UserEntity user, UserEntity seller, PostEntity post, Approval approval, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.user = user;
        this.seller = seller;
        this.post = post;
        this.approval = approval;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
}
