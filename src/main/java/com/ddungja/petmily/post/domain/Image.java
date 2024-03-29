package com.ddungja.petmily.post.domain;

import com.ddungja.petmily.common.domain.BaseTimeEntity;
import com.ddungja.petmily.post.domain.type.ImageType;
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
    private ImageType imageType;

    @JoinColumn(name = "post_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @Builder
    public Image(Long id, String url, ImageType imageType, Post post, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.url = url;
        this.imageType = imageType;
        this.post = post;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public Image(String thumbnailImage) {
        this.url = thumbnailImage;
        imageType = ImageType.POST;
    }
}
