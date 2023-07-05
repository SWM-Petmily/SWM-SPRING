package com.ddungja.app.post.controller.response;

import com.ddungja.app.post.domain.MainCategory;
import com.ddungja.app.post.domain.SubCategory;
import com.ddungja.app.post.domain.image.ImageType;
import com.ddungja.app.post.domain.post.*;
import com.ddungja.app.users.user.controller.response.ExperienceResponse;
import com.ddungja.app.users.user.controller.response.ProfileImageResponse;
import com.ddungja.app.users.user.controller.response.ProfileResponse;
import com.ddungja.app.users.user.controller.response.UserResponse;
import com.ddungja.app.users.user.domain.Profile;
import com.ddungja.app.users.user.domain.User;
import com.ddungja.app.users.user.domain.request.ExperienceCreateRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
public class PostDto {

    @Data
    public static class PostCreateRequest {

        private Long mainCategory;

        private Long subCategory;

        private String region;
        @Enumerated(EnumType.STRING)
        private Gender gender;

        private String birth;

        @Enumerated(EnumType.STRING)
        private Neutered neutered;

        private int money;
        private String breeding;
        private String environment;
        private String reason;

        private String personality;

        @Enumerated(EnumType.STRING)
        private Type vaccination;

        @Enumerated(EnumType.STRING)
        private Type registration;

        private List<ImageDto.ImageRequest> postImages;

        private List<ImageDto.ImageRequest> vaccinationImages;

        private List<ImageDto.ImageRequest> medicalCheckImages;

        private List<DiseaseDto.DiseaseRequest> diseases;



        public PostCreateRequest() {
        }

        public Post toEntity(User user, MainCategory mainCategory, SubCategory subCategory){
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
                    .status(PostStatus.Y)
                    .build();
        }

    }

    @Getter
    @Setter
    public static class PostResponse{

        private Long id;

        private Long mainCategory;

        private Long subCategory;

        private String region;
        @Enumerated(EnumType.STRING)
        private Gender gender;

        private String birth;

        @Enumerated(EnumType.STRING)
        private Neutered neutered;

        private int money;
        private String breeding;
        private String environment;
        private String reason;

        private String personality;

        @Enumerated(EnumType.STRING)
        private Type vaccination;

        @Enumerated(EnumType.STRING)
        private Type registration;


        @Builder
        public PostResponse(Long id, Long mainCategory, Long subCategory, String region, Gender gender, String birth, Neutered neutered, int money, String breeding, String environment, String reason, String personality, Type vaccination, Type registration) {
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

        public static PostDto.PostResponse from(Post post) {
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

}