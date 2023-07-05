package com.ddungja.app.post.controller.response;

import com.ddungja.app.post.domain.post.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;


@Getter
public class PostResponse{

    private final Long id;
    private final Long mainCategory;
    private final Long subCategory;
    private final String region;
    @Enumerated(EnumType.STRING)
    private GenderType gender;
    private String birth;
    @Enumerated(EnumType.STRING)
    private NeuteredType neutered;
    private int money;
    private String breeding;
    private String environment;
    private String reason;
    private String personality;
    @Enumerated(EnumType.STRING)
    private VaccinatedType vaccination;
    @Enumerated(EnumType.STRING)
    private PostStatusType registration;

    @Builder
    public PostResponse(Long id, Long mainCategory, Long subCategory, String region, GenderType gender, String birth, NeuteredType neutered, int money, String breeding, String environment, String reason, String personality, VaccinatedType vaccination, PostStatusType registration) {
        this.id = id;
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
        this.region = region;
        this.gender = gender;
        this.birth = birth;
        this.neutered = neutered;
        this.money = money;
        this.breeding = breeding;
        this.environment = environment;
        this.reason = reason;
        this.personality = personality;
        this.vaccination = vaccination;
        this.registration = registration;
    }

    public static PostResponse from(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .mainCategory(post.getMainCategory().getId())
                .subCategory(post.getSubCategory().getId())
                .region(post.getRegion())
                .gender(post.getGender())
                .birth(post.getBirth())
                .neutered(post.getNeutered())
                .money(post.getMoney())
                .breeding(post.getBreeding())
                .environment(post.getEnvironment())
                .reason(post.getReason())
                .personality(post.getPersonality())
                .vaccination(post.getVaccination())
                .registration(post.getRegistration())
                .build();
    }

}
