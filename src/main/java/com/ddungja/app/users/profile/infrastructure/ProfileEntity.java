package com.ddungja.app.users.profile.infrastructure;


import com.ddungja.app.common.domain.BaseTimeEntity;
import com.ddungja.app.users.profile.domain.Profile;
import com.ddungja.app.users.profileimage.infrastructure.ProfileImageEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "profiles")
public class ProfileEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long id;
    private String job;
    private String environment;
    private String people;
    private String comment;
    private String color;
    private String openTalk;
    private String region;
    private String isExperience;

    @OneToOne
    private ProfileImageEntity profileImage;

    @Builder
    private ProfileEntity(Long id, String job, String environment, String people, String comment, String color, String openTalk, String region, String isExperience, ProfileImageEntity profileImage, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.job = job;
        this.environment = environment;
        this.people = people;
        this.comment = comment;
        this.color = color;
        this.openTalk = openTalk;
        this.region = region;
        this.isExperience = isExperience;
        this.profileImage = profileImage;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public static ProfileEntity from(Profile profile) {
        return ProfileEntity.builder()
                .id(profile.getId())
                .job(profile.getJob())
                .environment(profile.getEnvironment())
                .people(profile.getPeople())
                .comment(profile.getComment())
                .color(profile.getColor())
                .openTalk(profile.getOpenTalk())
                .region(profile.getRegion())
                .isExperience(profile.getIsExperience())
                .profileImage(profile.getProfileImage())
                .createDate(profile.getCreateDate())
                .updateDate(profile.getUpdateDate())
                .build();
    }

    public Profile toDomain() {
        return Profile.builder()
                .id(id)
                .job(job)
                .environment(environment)
                .people(people)
                .comment(comment)
                .color(color)
                .openTalk(openTalk)
                .region(region)
                .isExperience(isExperience)
                .profileImage(profileImage)
                .createDate(createDate)
                .updateDate(updateDate)
                .build();
    }
}
