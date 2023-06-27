package com.ddungja.app.post.domain.post;

import com.ddungja.app.post.infrastructure.entity.MainCategoryEntity;
import com.ddungja.app.post.infrastructure.entity.SubCategoryEntity;
import com.ddungja.app.users.user.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Post {
    private final Long id;
    private final User user;
    private final MainCategoryEntity mainCategory;
    private final SubCategoryEntity subCategory;
    private final Gender gender;
    private final String birth;
    private final String name;
    private final String region;
    private final Neutered neutered;
    private final int money;
    private final String breeding;
    private final String environment;
    private final String reason;
    private final String vaccination;
    private final int views;
    private final int likes;
    private final int reports;
    private final int completion;
    private final int applys;
    private final PostStatus status;
    private final LocalDateTime createDate;
    private final LocalDateTime updateDate;

    @Builder
    private Post(Long id, User user, MainCategoryEntity mainCategory, SubCategoryEntity subCategory, Gender gender, String birth, String name, String region, Neutered neutered, int money, String breeding, String environment, String reason, String vaccination, int views, int likes, int reports, int completion, int applys, PostStatus status, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.user = user;
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
        this.gender = gender;
        this.birth = birth;
        this.name = name;
        this.region = region;
        this.neutered = neutered;
        this.money = money;
        this.breeding = breeding;
        this.environment = environment;
        this.reason = reason;
        this.vaccination = vaccination;
        this.views = views;
        this.likes = likes;
        this.reports = reports;
        this.completion = completion;
        this.applys = applys;
        this.status = status;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
}
