package com.ddungja.petmily.post.domain;


import com.ddungja.petmily.apply.domain.Apply;
import com.ddungja.petmily.common.domain.BaseTimeEntity;
import com.ddungja.petmily.common.exception.CustomException;
import com.ddungja.petmily.common.exception.ExceptionCode;
import com.ddungja.petmily.like.domain.Like;
import com.ddungja.petmily.post.domain.request.PostCreateRequest;
import com.ddungja.petmily.post.domain.type.CertifiedType;
import com.ddungja.petmily.post.domain.type.GenderType;
import com.ddungja.petmily.post.domain.type.NeuteredType;
import com.ddungja.petmily.post.domain.type.PostStatusType;
import com.ddungja.petmily.user.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.ddungja.petmily.common.exception.ExceptionCode.*;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "posts")
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "main_category_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MainCategory mainCategory;

    @JoinColumn(name = "sub_category_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private SubCategory subCategory;

    private String thumbnailImage;

    @OneToMany(mappedBy = "post")
    private List<Like> like = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<Apply> applys = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<Disease> diseases = new ArrayList<>();


    @Enumerated(EnumType.STRING)
    private GenderType gender;
    private String birth;
    private int age;
    private String name;
    private String region;

    @Enumerated(EnumType.STRING)
    private NeuteredType neutered;
    private int money;
    private String reason; // 분양 이유
    private String advantage; // 장점, 자랑
    private String disadvantage; // 단점, 주의할 점
    private String averageCost;// 평균비용
    private String adopter; // 분양자

    @Enumerated(EnumType.STRING)
    private PostStatusType status; // 분양상태

    @Enumerated(EnumType.STRING)
    private CertifiedType isRegistered = CertifiedType.NONE; // 분양 등록 여부

    @Enumerated(EnumType.STRING)
    private CertifiedType isVaccinated = CertifiedType.NONE; // 예방접종 여부

    @Enumerated(EnumType.STRING)
    private CertifiedType isMedicalChecked = CertifiedType.NONE; // 건강검진 여부
    private int views;
    private int reports;

    @Builder
    public Post(Long id, User user, MainCategory mainCategory, SubCategory subCategory, String thumbnailImage, List<Like> like, List<Image> images, List<Apply> applys, List<Disease> diseases, GenderType gender, String birth, int age, String name, String region, NeuteredType neutered, int money, String reason, String advantage, String disadvantage, String averageCost, String adopter, PostStatusType status, CertifiedType isRegistered, CertifiedType isVaccinated, CertifiedType isMedicalChecked, int views, int reports) {
        this.id = id;
        this.user = user;
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
        this.thumbnailImage = thumbnailImage;
        this.like = like;
        this.images = images;
        this.applys = applys;
        this.diseases = diseases;
        this.gender = gender;
        this.birth = birth;
        this.age = age;
        this.name = name;
        this.region = region;
        this.neutered = neutered;
        this.money = money;
        this.reason = reason;
        this.advantage = advantage;
        this.disadvantage = disadvantage;
        this.averageCost = averageCost;
        this.adopter = adopter;
        this.status = status;
        this.isRegistered = isRegistered;
        this.isVaccinated = isVaccinated;
        this.isMedicalChecked = isMedicalChecked;
        this.views = views;
        this.reports = reports;
    }

    public static Post from(PostCreateRequest postCreateRequest, User user, MainCategory mainCategory, SubCategory subCategory) {
        int[] date = Arrays.stream(postCreateRequest.getBirth().split("-")).mapToInt(Integer::parseInt).toArray();
        LocalDate start = LocalDate.of(date[0], date[1], 1);
        LocalDate end = LocalDate.now();
        return Post.builder()
                .user(user)
                .mainCategory(mainCategory)
                .subCategory(subCategory)
                .name(postCreateRequest.getName())
                .gender(postCreateRequest.getGender())
                .birth(postCreateRequest.getBirth())
                .region(postCreateRequest.getRegion())
                .money(postCreateRequest.getMoney())
                .neutered(postCreateRequest.getNeutered())
                .age((int) ChronoUnit.MONTHS.between(start, end))
                .reason(postCreateRequest.getReason())
                .advantage(postCreateRequest.getAdvantage())
                .disadvantage(postCreateRequest.getDisadvantage())
                .averageCost(postCreateRequest.getAverageCost())
                .adopter(postCreateRequest.getAdopter())
                .status(PostStatusType.SAVE)
                .isRegistered(postCreateRequest.getIsRegistered() ? CertifiedType.CERTIFIED : CertifiedType.NONE)
                .isVaccinated(CertifiedType.NONE)
                .isMedicalChecked(CertifiedType.NONE)
                .views(0)
                .reports(0)
                .build();
    }

    public void createThumbnailImage( List<Image> images) {
        this.thumbnailImage = images.get(0).getUrl();
    }

    public void uploadImages(List<Image> uploadImages) {
        this.images = uploadImages;
    }

    public void certifyRegistration(Long userId) {
        matchUser(userId);
        this.isRegistered = CertifiedType.CERTIFIED;
    }

    public void certifyVaccination() {
        this.isVaccinated = CertifiedType.WAITING;
    }

    public void certifyMedicalCheck() {
        this.isMedicalChecked = CertifiedType.WAITING;
    }

    public void deletePost(Long userId) {
        matchUser(userId);
        if (status == PostStatusType.COMPLETE ) {
            throw new CustomException(POST_STATUS_COMPLETE);
        }
        if (status== PostStatusType.REPORT) {
            throw new CustomException(POST_STATUS_REPORT);
        }
        this.status = PostStatusType.DELETE;
    }

    public void complete(Long userId) {
        matchUser(userId);
        if(status==PostStatusType.DELETE){
            throw new CustomException(POST_STATUS_DELETE);
        }
        this.status = PostStatusType.COMPLETE;
    }

    public void report() {
        isDelete();
        reports++;
        if (reports >= 5) reportPost();
    }

    private void isDelete() {
        if (this.status.equals(PostStatusType.DELETE)) {
            throw new CustomException(ExceptionCode.POST_STATUS_DELETE);
        }
    }

    private void reportPost() {
        this.status = PostStatusType.REPORT;
    }

    public void matchUser(Long userId) {
        if (user.getId().equals(userId)) {
            throw new CustomException(POST_USER_NOT_MATCH);
        }
    }
}
