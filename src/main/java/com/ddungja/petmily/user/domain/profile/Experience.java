package com.ddungja.petmily.user.domain.profile;

import com.ddungja.petmily.common.domain.BaseTimeEntity;
import com.ddungja.petmily.user.domain.request.ExperienceCreateRequest;
import com.ddungja.petmily.user.domain.request.ExperienceUpdateRequest;
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

    @JoinColumn(name = "profile_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @ManyToOne(fetch = FetchType.LAZY)
    private Profile profile;

    @Builder
    public Experience(Long id, String species, int period, Profile profile, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.species = species;
        this.period = period;
        this.profile = profile;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
    public static Experience from(ExperienceCreateRequest experienceRequest, Profile profile) {
        return Experience.builder()
                .species(experienceRequest.getSpecies())
                .period(experienceRequest.getPeriod())
                .profile(profile)
                .build();
    }
    public static Experience from(ExperienceUpdateRequest experienceRequest, Profile profile) {
        return Experience.builder()
                .species(experienceRequest.getSpecies())
                .period(experienceRequest.getPeriod())
                .profile(profile)
                .build();
    }

}
