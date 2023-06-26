package com.ddungja.app.post.domain.image;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Image {
    private final Long id;
    private final String url;
    private final Type imageType;
    private final LocalDateTime createDate;
    private final LocalDateTime updateDate;

    @Builder
    private Image(Long id, String url, Type imageType, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.url = url;
        this.imageType = imageType;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
}
