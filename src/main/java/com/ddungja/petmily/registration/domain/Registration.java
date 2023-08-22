package com.ddungja.petmily.registration.domain;


import com.ddungja.petmily.common.domain.BaseTimeEntity;
import com.ddungja.petmily.post.domain.SubCategory;
import com.ddungja.petmily.post.domain.type.GenderType;
import com.ddungja.petmily.post.domain.type.NeuteredType;
import com.ddungja.petmily.registration.controller.response.RegistrationApiResponse;
import com.ddungja.petmily.user.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "registration")
public class Registration extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registration_id")
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String registrationNumber; // 등록번호

    private String petName; // 반려동물 이름

    @JoinColumn(name = "sub_category_id")
    @ManyToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
    private SubCategory petSubCategory;

    @Enumerated(EnumType.STRING)
    private GenderType petGender; // 반려동물 성별

    @Enumerated(EnumType.STRING)
    private NeuteredType petNeutered; // 반려동물 중성화 여부

    @Builder
    public Registration(Long id, User user, String registrationNumber, String petName, SubCategory petSubCategory,  GenderType petGender, NeuteredType petNeutered) {
        this.id = id;
        this.user = user;
        this.registrationNumber = registrationNumber;
        this.petName = petName;
        this.petSubCategory = petSubCategory;
        this.petGender = petGender;
        this.petNeutered = petNeutered;
    }

    public static Registration from(RegistrationApiResponse.RegistrationApiItem registrationApiResult, User user, SubCategory petSubcategory){
        return Registration.builder()
                .user(user)
                .petSubCategory(petSubcategory)
                .registrationNumber(registrationApiResult.getDogRegNo())
                .petName(registrationApiResult.getDogNm())
                .petGender(registrationApiResult.getSexNm().equals("암컷")? GenderType.FEMALE : GenderType.MALE)
                .petNeutered(registrationApiResult.getNeuterYn().equals("중성") ? NeuteredType.YES : NeuteredType.NO)
                .build();
    }

}
