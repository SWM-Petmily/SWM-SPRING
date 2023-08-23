package com.ddungja.petmily.post.controller.response;

import com.ddungja.petmily.post.domain.Disease;
import com.ddungja.petmily.post.domain.Image;
import com.ddungja.petmily.post.domain.Post;
import com.ddungja.petmily.post.domain.type.*;
import com.ddungja.petmily.user.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class PostGetResponse {
    private final Long postId;
    private final Long writerId;
    private final int level;
    private final String writer;
    private final Long writerProfileImage;
    private final String mainCategory;
    private final String subCategory;
    private final String name;
    private final GenderType gender;
    private final String region;
    private final int age;
    private final String registered;
    private final String vaccinated;
    private final String medicalChecked;
    private final String neutered;
    private final List<String> diseases;
    private final int money;
    private final String reason; // 분양 이유
    private final String advantage; // 장점, 자랑
    private final String disadvantage; // 단점, 주의할 점
    private final String averageCost;// 평균비용
    private final String adopter; // 분양자
    private final PostStatusType status; // 분양상태
    private final List<ImageResponse> images;
    private final Boolean isWriter;
    private final Boolean isLike;
    private final Boolean isApply;
    private final int likeCount;

    @Builder
    public PostGetResponse(Long postId, Long writerId, String writer, Long writerProfileImage, int level, String mainCategory, String subCategory, String name, GenderType gender, String region, int age, String registered, String vaccinated, String medicalChecked, String neutered, List<String> diseases, int money, String reason, String advantage, String disadvantage, String averageCost, String adopter, PostStatusType status, List<ImageResponse> images, Boolean isWriter, Boolean isLike, Boolean isApply, int likeCount) {
        this.postId = postId;
        this.writerId = writerId;
        this.writer = writer;
        this.level = level;
        this.writerProfileImage = writerProfileImage;
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
        this.name = name;
        this.gender = gender;
        this.region = region;
        this.age = age;
        this.registered = registered;
        this.vaccinated = vaccinated;
        this.medicalChecked = medicalChecked;
        this.neutered = neutered;
        this.diseases = diseases;
        this.money = money;
        this.reason = reason;
        this.advantage = advantage;
        this.disadvantage = disadvantage;
        this.averageCost = averageCost;
        this.adopter = adopter;
        this.status = status;
        this.images = images;
        this.isWriter = isWriter;
        this.isLike = isLike;
        this.isApply = isApply;
        this.likeCount = likeCount;
    }

    public static PostGetResponse from(Post post, List<Image> images, int likeCount){
        return PostGetResponse.builder()
                .postId(post.getId())
                .writerId(null)
                .writer(post.getUser().getNickname())
                .writerProfileImage(post.getUser().getProfileImage().getId())
                .mainCategory(post.getMainCategory().getName())
                .subCategory(post.getSubCategory().getName())
                .name(post.getName())
                .gender(post.getGender())
                .region(post.getRegion())
                .age(post.getAge())
                .registered(post.getIsRegistered() == CertifiedType.CERTIFIED? "인증 완료" : "-")
                .vaccinated(post.getIsVaccinated() == CertifiedType.CERTIFIED? "인증 완료" : "-")
                .medicalChecked(post.getIsMedicalChecked() == CertifiedType.CERTIFIED? "인증 완료" : "-")
                .neutered(post.getNeutered() == NeuteredType.YES? "완료" : "미완료")
                .diseases(post.getDiseases().stream().map(Disease::getName).toList())
                .money(post.getMoney())
                .reason(post.getReason())
                .advantage(post.getAdvantage())
                .disadvantage(post.getDisadvantage())
                .averageCost(post.getAverageCost())
                .adopter(post.getAdopter())
                .status(post.getStatus())
                .images(images.stream().map(ImageResponse::from).toList())
                .isWriter(false)
                .isLike(false)
                .isApply(false)
                .likeCount(likeCount)
                .build();
    }

    public static PostGetResponse from(User user, Post post,List<Image> images,Boolean isApply, Boolean isLike, int likeCount){
        return PostGetResponse.builder()
                .postId(post.getId())
                .writerId(post.getUser().getId())
                .writer(post.getUser().getNickname())
                .writerProfileImage(post.getUser().getProfileImage().getId())
                .mainCategory(post.getMainCategory().getName())
                .subCategory(post.getSubCategory().getName())
                .name(post.getName())
                .gender(post.getGender())
                .region(post.getRegion())
                .age(post.getAge())
                .registered(post.getIsRegistered() == CertifiedType.CERTIFIED? "인증 완료" : "-")
                .vaccinated(post.getIsVaccinated() == CertifiedType.CERTIFIED? "인증 완료" : "-")
                .medicalChecked(post.getIsMedicalChecked() == CertifiedType.CERTIFIED? "인증 완료" : "-")
                .neutered(post.getNeutered() == NeuteredType.YES? "완료" : "미완료")
                .diseases(post.getDiseases().stream().map(Disease::getName).toList())
                .money(post.getMoney())
                .reason(post.getReason())
                .advantage(post.getAdvantage())
                .disadvantage(post.getDisadvantage())
                .averageCost(post.getAverageCost())
                .adopter(post.getAdopter())
                .status(post.getStatus())
                .images(images.stream().map(ImageResponse::from).toList())
                .isWriter(post.getUser().getId().equals(user.getId()))
                .isLike(isLike)
                .isApply(isApply)
                .likeCount(likeCount)
                .build();
    }
}
