package com.ddungja.app.users.user.infrastructure.entity;


import com.ddungja.app.common.domain.BaseTimeEntity;
import com.ddungja.app.users.user.domain.Profile;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private int people;
    private String comment;
    private String color;
    private String openTalk;
    private String region;
    private boolean isExperience;

    @JoinColumn(name = "profile_image_id")
    @ManyToOne
    private ProfileImageEntity profileImage;

    @JoinColumn(name = "user_id")
    @OneToOne
    private UserEntity user;

    @OneToMany(mappedBy = "profile")
    private List<ExperienceEntity> experiences = new ArrayList<>();

    @Builder
    private ProfileEntity(Long id, String job, String environment, int people, String comment, String color, String openTalk, String region, boolean isExperience, UserEntity user,  List<ExperienceEntity> experiences, ProfileImageEntity profileImage, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.job = job;
        this.environment = environment;
        this.people = people;
        this.comment = comment;
        this.color = color;
        this.openTalk = openTalk;
        this.region = region;
        this.isExperience = isExperience;
        this.experiences = experiences;
        this.user = user;
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
                .isExperience(profile.isExperience())
                .user(UserEntity.from(profile.getUser()))
                .profileImage(ProfileImageEntity.from(profile.getProfileImage()))
                .createDate(profile.getCreateDate())
                .updateDate(profile.getUpdateDate())
                .build();
    }

    public Profile toDomainExperience() {
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
                .user(user.toDomain())
                .profileImage(profileImage.toDomain())
                .createDate(createDate)
                .updateDate(updateDate)
                .experiences(experiences.stream().map(ExperienceEntity::toDomain).toList())
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
                .user(user.toDomain())
                .profileImage(profileImage.toDomain())
                .createDate(createDate)
                .updateDate(updateDate)
                .build();
    }
}
