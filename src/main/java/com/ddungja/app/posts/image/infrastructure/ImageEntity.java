package com.ddungja.app.posts.image.infrastructure;

import com.ddungja.app.common.domain.BaseTimeEntity;
import com.ddungja.app.posts.image.domain.Image;
import com.ddungja.app.posts.image.domain.Type;
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
public class ImageEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;
    private String url;

    @Enumerated(EnumType.STRING)
    private Type imageType;

    @Builder
    private ImageEntity(Long id, String url, Type imageType, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.url = url;
        this.imageType = imageType;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public static ImageEntity from(Image image) {
        return ImageEntity.builder()
                .id(image.getId())
                .url(image.getUrl())
                .imageType(image.getImageType())
                .createDate(image.getCreateDate())
                .updateDate(image.getUpdateDate())
                .build();
    }

    public Image toDomain() {
        return Image.builder()
                .id(id)
                .url(url)
                .imageType(imageType)
                .createDate(createDate)
                .updateDate(updateDate)
                .build();
    }


}
