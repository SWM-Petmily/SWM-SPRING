package com.ddungja.app.apply.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "apply_experiences")
public class ApplyExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apply_experience_id")
    private Long id;
    private String species;
    private int period;
    @JoinColumn(name = "apply_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Apply apply;


    @Builder
    private ApplyExperience(Long id, String species, int period, Apply apply) {
        this.id = id;
        this.species = species;
        this.period = period;
        this.apply = apply;
    }
}
