package com.ddungja.petmily.user.domain.profile;


import com.ddungja.petmily.common.domain.BaseTimeEntity;
import com.ddungja.petmily.user.domain.request.MyProfileCreateRequest;
import com.ddungja.petmily.user.domain.request.ProfileUpdateRequest;
import com.ddungja.petmily.user.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @JoinColumn(name = "profile_image_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @ManyToOne(fetch = FetchType.LAZY)
    private ProfileImage profileImage;

    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Experience> experiences = new ArrayList<>();

    @Builder
    public Profile(Long id, String job, String environment, int people, String comment, String openTalk, String region, boolean isExperience, User user, List<Experience> experiences, ProfileImage profileImage) {
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
    }

    public static Profile from(MyProfileCreateRequest profileCreateRequest, ProfileImage profileImage, User user) {
        Profile profile = Profile.builder()
                .job(profileCreateRequest.getJob())
                .environment(profileCreateRequest.getEnvironment())
                .people(profileCreateRequest.getPeople())
                .comment(profileCreateRequest.getComment())
                .openTalk(profileCreateRequest.getOpenTalk())
                .region(profileCreateRequest.getRegion())
                .experiences(new ArrayList<>())
                .isExperience(profileCreateRequest.getIsExperience())
                .profileImage(profileImage)
                .user(user)
                .build();
        if (profileCreateRequest.getIsExperience().equals(Boolean.TRUE)) {
            profileCreateRequest.getExperiences().forEach(experience -> profile.addExperience(Experience.from(experience, profile)));
        }
        return profile;
    }

    private void addExperience(Experience experience) {
        experiences.add(experience);
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
        deleteExperiences();
        if (!profileUpdateRequest.getExperiences().isEmpty()) {
            profileUpdateRequest.getExperiences().forEach(experience -> this.experiences.add(Experience.from(experience, this)));
        }
    }

    private void deleteExperiences() {
        this.experiences.clear();
    }

    public void delete() {
        experiences.clear();
    }
}
