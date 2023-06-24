package com.ddungja.app.users.profile.infrastructure;


import com.ddungja.app.common.domain.BaseTimeEntity;
import com.ddungja.app.users.profileimage.infrastructure.ProfileImageEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

}
