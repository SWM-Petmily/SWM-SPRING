package com.ddungja.petmily.registration.controller.response;

import com.ddungja.petmily.post.domain.type.GenderType;
import com.ddungja.petmily.post.domain.type.NeuteredType;
import com.ddungja.petmily.registration.domain.Registration;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SelectRegistrationResponse {
    private final Long mainCategory;
    private final String mainCategoryName;
    private final Long subCategory;
    private final String subCategoryName;
    private final String name;
    private final GenderType gender;
    private final NeuteredType neutered;

    @Builder
    public SelectRegistrationResponse(Long mainCategory, String mainCategoryName, Long subCategory, String subCategoryName, String name, GenderType gender, NeuteredType neutered) {
        this.mainCategory = mainCategory;
        this.mainCategoryName = mainCategoryName;
        this.subCategory = subCategory;
        this.subCategoryName = subCategoryName;
        this.name = name;
        this.gender = gender;
        this.neutered = neutered;
    }


    public static SelectRegistrationResponse from(Registration registration) {
        return SelectRegistrationResponse.builder()
                .mainCategory(registration.getPetSubCategory().getMainCategory().getId())
                .mainCategoryName(registration.getPetSubCategory().getMainCategory().getName())
                .subCategory(registration.getPetSubCategory().getId())
                .subCategoryName(registration.getPetSubCategory().getName())
                .name(registration.getPetName())
                .gender(registration.getPetGender())
                .neutered(registration.getPetNeutered())
                .build();
    }
}
