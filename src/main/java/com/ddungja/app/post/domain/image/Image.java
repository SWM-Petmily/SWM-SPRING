package com.ddungja.app.post.domain.image;

import com.ddungja.app.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "images")
public class Image extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;
    private String url;

    @Enumerated(EnumType.STRING)
    private Type imageType;

    @Builder
    private Image(Long id, String url, Type imageType, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.url = url;
        this.imageType = imageType;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }



}
