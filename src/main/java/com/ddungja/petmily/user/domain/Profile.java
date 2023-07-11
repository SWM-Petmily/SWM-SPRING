package com.ddungja.petmily.user.domain;


import com.ddungja.petmily.common.domain.BaseTimeEntity;
import com.ddungja.petmily.user.domain.request.MyProfileCreateRequest;
import com.ddungja.petmily.user.domain.request.ProfileUpdateRequest;
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
public class Profile extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long id;
    private String job;
    private String environment;
    private int people;
    private String comment;
    private String openTalk;
    private String region;
    private boolean isExperience;

    @JoinColumn(name = "profile_image_id")
    @ManyToOne
    private ProfileImage profileImage;

    @JoinColumn(name = "user_id")
    @OneToOne
    private User user;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Experience> experiences = new ArrayList<>();

    @Builder
    private Profile(Long id, String job, String environment, int people, String comment, String openTalk, String region, boolean isExperience, User user, List<Experience> experiences, ProfileImage profileImage, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.job = job;
        this.environment = environment;
        this.people = people;
        this.comment = comment;
        this.openTalk = openTalk;
        this.region = region;
        this.isExperience = isExperience;
        this.experiences = experiences;
        this.user = user;
        this.profileImage = profileImage;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }


    public static Profile from(MyProfileCreateRequest profileCreateRequest, ProfileImage profileImage, User user) {
        return Profile.builder()
                .job(profileCreateRequest.getJob())
                .environment(profileCreateRequest.getEnvironment())
                .people(profileCreateRequest.getPeople())
                .comment(profileCreateRequest.getComment())
                .openTalk(profileCreateRequest.getOpenTalk())
                .region(profileCreateRequest.getRegion())
                .isExperience(profileCreateRequest.getIsExperience())
                .profileImage(profileImage)
                .user(user)
                .build();

    }

    public void update(ProfileUpdateRequest profileUpdateRequest, ProfileImage profileImage) {
        this.job = profileUpdateRequest.getJob();
        this.environment = profileUpdateRequest.getEnvironment();
        this.people = profileUpdateRequest.getPeople();
        this.comment = profileUpdateRequest.getComment();
        this.openTalk = profileUpdateRequest.getOpenTalk();
        this.region = profileUpdateRequest.getRegion();
        this.profileImage = profileImage;
        this.isExperience = profileUpdateRequest.getIsExperience();
    }

    public void deleteExperiences() {
        this.experiences.clear();
    }
}
