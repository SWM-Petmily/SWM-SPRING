package com.ddungja.app.users.user.infrastructure.entity;

import com.ddungja.app.common.domain.BaseTimeEntity;
import com.ddungja.app.users.user.domain.ProfileImage;
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
public class ProfileImageEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_image_id")
    private Long id;

    @Lob
    private String url;


    @Builder
    private ProfileImageEntity(Long id, String url, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.url = url;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }


    public static ProfileImageEntity from(ProfileImage profileImage) {
        return ProfileImageEntity.builder()
                .id(profileImage.getId())
                .url(profileImage.getUrl())
                .createDate(profileImage.getCreateDate())
                .updateDate(profileImage.getUpdateDate())
                .build();
    }

    public ProfileImage toDomain() {
        return ProfileImage.builder()
                .id(id)
                .url(url)
                .createDate(createDate)
                .updateDate(updateDate)
                .build();
    }
}
