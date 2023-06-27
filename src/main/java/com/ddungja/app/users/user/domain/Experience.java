package com.ddungja.app.users.user.domain;

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
@Table(name = "experiences")
public class Experience extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "experience_id")
    private Long id;
    private String species;
    private int period;

    @JoinColumn(name = "profile_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Profile profile;

    @Builder
    private Experience(Long id, String species, int period, Profile profile, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.species = species;
        this.period = period;
        this.profile = profile;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }



    public static Experience from(Experience experience, Profile profile) {
        return Experience.builder()
                .id(experience.getId())
                .species(experience.getSpecies())
                .period(experience.getPeriod())
                .profile(profile)
                .createDate(experience.getCreateDate())
                .updateDate(experience.getUpdateDate())
                .build();
    }
}
