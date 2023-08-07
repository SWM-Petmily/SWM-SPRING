package com.ddungja.petmily.post.domain.request;

import com.ddungja.petmily.post.domain.type.GenderType;
import com.ddungja.petmily.post.domain.type.NeuteredType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PostCreateRequest {
    private final String mainCategory;
    private final String subCategory;
    private final String name;
    private final String region;
    private final GenderType gender;
    private final String birth;
    private final NeuteredType neutered;
    private final int money;
    private final String reason; // 분양 이유
    private final String advantage; // 장점, 자랑
    private final String disadvantage; // 단점, 주의할 점
    private final String averageCost;// 평균비용
    private final String adopter; // 분양자
    private final List<DiseaseRequest> diseases;
    private final Boolean isRegistered;

    @Builder
    private PostCreateRequest(String mainCategory, String subCategory, String name, String region, GenderType gender, String birth, NeuteredType neutered, int money, String reason, String advantage, String disadvantage, String averageCost, String adopter, List<ImageCreateRequest> postImages, List<ImageCreateRequest> vaccinationImages, List<ImageCreateRequest> medicalCheckImages, List<DiseaseRequest> diseases, Boolean isRegistered) {
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
        this.name = name;
        this.region = region;
        this.gender = gender;
        this.birth = birth;
        this.neutered = neutered;
        this.money = money;
        this.reason = reason;
        this.advantage = advantage;
        this.disadvantage = disadvantage;
        this.averageCost = averageCost;
        this.adopter = adopter;
        this.diseases = diseases;
        this.isRegistered = isRegistered;
    }

}

