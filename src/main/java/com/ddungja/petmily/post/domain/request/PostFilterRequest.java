package com.ddungja.petmily.post.domain.request;

import com.ddungja.petmily.post.domain.type.GenderType;
import com.ddungja.petmily.post.domain.type.NeuteredType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostFilterRequest { // 인증정보 아직 안함.
    private final String region;
    private final String mainCategory;
    private final String subCategory;
    private final GenderType genderType;
    private final NeuteredType neuteredType;
    private final Integer ageFrom; // 이상
    private final Integer ageTo; // 이하
    private final Integer moneyFrom; // 이상
    private final Integer moneyTo; // 이하

    @Builder
    public PostFilterRequest(String region, String mainCategory, String subCategory, GenderType genderType, NeuteredType neuteredType, Integer ageFrom, Integer ageTo, Integer moneyFrom, Integer moneyTo) {
        this.region = region;
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
        this.genderType = genderType;
        this.neuteredType = neuteredType;
        this.ageFrom = ageFrom;
        this.ageTo = ageTo;
        this.moneyFrom = moneyFrom;
        this.moneyTo = moneyTo;
    }
}
