package com.ddungja.app.post.domain.personality;


import com.ddungja.app.post.infrastructure.entity.PostEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Personality {
    private final Long id;
    private final PostEntity post;
    private final String personality;
    private final PersonalityType type;
    private final LocalDateTime createDate;
    private final LocalDateTime updateDate;

    @Builder
    private Personality(Long id, PostEntity post, String personality, PersonalityType type, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.post = post;
        this.personality = personality;
        this.type = type;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
}
