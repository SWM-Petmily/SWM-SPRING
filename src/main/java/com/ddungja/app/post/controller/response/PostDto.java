package com.ddungja.app.post.controller.response;

import com.ddungja.app.post.domain.MainCategory;
import com.ddungja.app.post.domain.SubCategory;
import com.ddungja.app.post.domain.image.ImageType;
import com.ddungja.app.post.domain.post.*;
import com.ddungja.app.users.user.domain.User;
import com.ddungja.app.users.user.domain.request.ExperienceCreateRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
}