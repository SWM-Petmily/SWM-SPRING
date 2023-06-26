package com.ddungja.app.post.domain;


import com.ddungja.app.post.infrastructure.entity.PostEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Disease {
    private final Long id;
    private final PostEntity post;
    private final LocalDateTime createDate;
    private final LocalDateTime updateDate;


    @Builder
    private Disease(Long id, PostEntity post, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.post = post;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
}
