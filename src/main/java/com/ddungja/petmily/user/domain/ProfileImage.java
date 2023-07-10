package com.ddungja.petmily.user.domain;

import com.ddungja.petmily.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "profile_images")
public class ProfileImage extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_image_id")
    private Long id;
    @Lob
    private String url;
    @Builder
    private ProfileImage(Long id, String url, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.url = url;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
    public void update(Long profileImageId) {
        this.id = profileImageId;
    }
}
