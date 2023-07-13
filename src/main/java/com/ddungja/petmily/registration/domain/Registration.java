package com.ddungja.petmily.registration.domain;


import com.ddungja.petmily.common.domain.BaseTimeEntity;
import com.ddungja.petmily.post.domain.SubCategory;
import com.ddungja.petmily.post.domain.type.GenderType;
import com.ddungja.petmily.post.domain.type.NeuteredType;
import com.ddungja.petmily.user.domain.User;
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

    private String dogRegNo; // 등록번호

    private String pet_name; // 반려동물 이름

    @JoinColumn(name = "sub_category_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private SubCategory pet_subCategory;

    //private String pet_birth; // 반려동물 생일

    private GenderType pet_gender; // 반려동물 성별

    @Enumerated(EnumType.STRING)
    private NeuteredType pet_neutered; // 반려동물 중성화 여부

    @Builder
    public Registration(Long id, User user, String registration_number, String pet_name, SubCategory pet_subCategory, String pet_birth, GenderType pet_gender, NeuteredType pet_neutered) {
        this.id = id;
        this.user = user;
        this.dogRegNo = registration_number;
        this.pet_name = pet_name;
        this.pet_subCategory = pet_subCategory;
        //this.pet_birth = pet_birth;
        this.pet_gender = pet_gender;
        this.pet_neutered = pet_neutered;
    }


    public static Registration from(RegistrationApiItem registrationApiResult, User user, SubCategory petSubcategory){
        return Registration.builder()
                .user(user)
                .pet_subCategory(petSubcategory)
                .registration_number(registrationApiResult.getDogRegNo())
                .pet_name(registrationApiResult.getDogNm())
                .pet_gender(registrationApiResult.getSexNm().equals("암컷")? GenderType.MALE : GenderType.FEMALE)
                .pet_neutered(registrationApiResult.getNeuterYn().equals("중성") ? NeuteredType.YES : NeuteredType.NO)
                .build();
    }
}
