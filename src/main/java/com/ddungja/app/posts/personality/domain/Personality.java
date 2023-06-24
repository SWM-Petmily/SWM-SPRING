package com.ddungja.app.posts.personality.domain;


import com.ddungja.app.posts.post.infrastructure.PostEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Personality {
    private final Long id;
    private final PostEntity post;
    private final String personality;
    private final Type type;
    private final LocalDateTime createDate;
    private final LocalDateTime updateDate;

    @Builder
    private Personality(Long id, PostEntity post, String personality, Type type, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.post = post;
        this.personality = personality;
        this.type = type;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
}
