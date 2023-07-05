package com.ddungja.app.post.domain.request;

import com.ddungja.app.post.domain.MainCategory;
import com.ddungja.app.post.domain.SubCategory;
import com.ddungja.app.post.domain.post.*;
import com.ddungja.app.users.user.domain.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class PostCreateRequest {
    private Long mainCategory;
    private Long subCategory;
    private String region;
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
    private List<ImageCreateRequest> postImages;
    private List<ImageCreateRequest> vaccinationImages;
    private List<ImageCreateRequest> medicalCheckImages;
    private List<DiseaseRequest> diseases;

    @Builder
    private PostCreateRequest(Long mainCategory, Long subCategory, String region, GenderType gender, String birth, NeuteredType neutered, int money, String breeding, String environment, String reason, String personality, VaccinatedType vaccination, PostStatusType registration, List<ImageCreateRequest> postImages, List<ImageCreateRequest> vaccinationImages, List<ImageCreateRequest> medicalCheckImages, List<DiseaseRequest> diseases) {
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
        this.postImages = postImages;
        this.vaccinationImages = vaccinationImages;
        this.medicalCheckImages = medicalCheckImages;
        this.diseases = diseases;
    }

    public Post toEntity(User user, MainCategory mainCategory, SubCategory subCategory) {
        return Post.builder()
                .user(user)
                .mainCategory(mainCategory)
                .subCategory(subCategory)
                .gender(gender)
                .birth(birth)
                .region(region)
                .neutered(neutered)
                .money(money)
                .breeding(breeding)
                .environment(environment)
                .reason(reason)
                .personality(personality)
                .registration(registration)
                .vaccination(vaccination)
                .views(0)
                .reports(0)
                .completion(0)
                .status(PostStatusType.SAVE)
                .build();
    }

}

