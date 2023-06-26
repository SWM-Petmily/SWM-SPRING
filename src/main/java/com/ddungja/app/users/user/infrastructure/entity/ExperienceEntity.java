package com.ddungja.app.users.user.infrastructure.entity;

import com.ddungja.app.common.domain.BaseTimeEntity;
import com.ddungja.app.users.user.domain.Experience;
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
public class ExperienceEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "experience_id")
    private Long id;
    private String species;
    private int period;

    @JoinColumn(name = "profile_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProfileEntity profile;

    @Builder
    private ExperienceEntity(Long id, String species, int period, ProfileEntity profile, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.species = species;
        this.period = period;
        this.profile = profile;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public static ExperienceEntity from(Experience experience) {
        return ExperienceEntity.builder()
                .id(experience.getId())
                .species(experience.getSpecies())
                .period(experience.getPeriod())
                .profile(ProfileEntity.from(experience.getProfile()))
                .createDate(experience.getCreateDate())
                .updateDate(experience.getUpdateDate())
                .build();
    }

    public Experience toDomain() {
        return Experience.builder()
                .id(id)
                .species(species)
                .period(period)
                .profile(profile.toDomain())
                .createDate(createDate)
                .updateDate(updateDate)
                .build();
    }
}
