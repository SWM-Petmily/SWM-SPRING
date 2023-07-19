package com.ddungja.petmily.post.domain.request;

import com.ddungja.petmily.post.domain.type.GenderType;
import com.ddungja.petmily.post.domain.type.NeuteredType;
import com.ddungja.petmily.post.domain.type.RangeType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostFilterRequest { // 책임비, 인증정보 아직 안함.
    private String region;
    private String mainCategory;
    private String subCategory;
    private GenderType genderType;
    private NeuteredType neuteredType;
    private int birth;
    private RangeType rangeType;


    @Builder
    public PostFilterRequest(String region, String mainCategory, String subCategory, GenderType genderType, NeuteredType neuteredType, int birth, RangeType rangeType) {
        this.region = region;
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
        this.genderType = genderType;
        this.neuteredType = neuteredType;
        this.birth = birth;
        this.rangeType = rangeType;
    }

}
