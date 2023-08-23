package com.ddungja.petmily.apply.domain;


import com.ddungja.petmily.apply.domain.request.ApplyCreateRequest;
import com.ddungja.petmily.apply.domain.request.ApplyUpdateRequest;
import com.ddungja.petmily.common.domain.BaseTimeEntity;
import com.ddungja.petmily.post.domain.Post;
import com.ddungja.petmily.user.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "applys", indexes = @Index(name = "idx_applys_user_id", columnList = "apply_id, approval"))
@Where(clause = "approval != 'CANCEL'")
public class Apply extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apply_id")
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "seller_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User seller;

    @JoinColumn(name = "post_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @Enumerated(EnumType.STRING)
    private ApprovalType approval;

    @OneToMany(mappedBy = "apply", cascade = CascadeType.ALL)
    private List<ApplyExperience> applyExperiences = new ArrayList<>();

    private String job;
    private String environment;
    private int people;
    private String comment;
    private String openTalk;
    private String region;
    private boolean isExperience;
    private String url;

    @Builder
    public Apply(Long id, User user, User seller, Post post, ApprovalType approval, List<ApplyExperience> applyExperiences, String job, String environment, int people, String comment, String openTalk, String region, boolean isExperience, String url) {
        this.id = id;
        this.user = user;
        this.seller = seller;
        this.post = post;
        this.approval = approval;
        this.applyExperiences = applyExperiences;
        this.job = job;
        this.environment = environment;
        this.people = people;
        this.comment = comment;
        this.openTalk = openTalk;
        this.region = region;
        this.isExperience = isExperience;
        this.url = url;
    }

    public static Apply from(ApplyCreateRequest applyCreateRequest, User user, Post post) {
        return Apply.builder()
                .user(user)
                .post(post)
                .approval(ApprovalType.WAITING)
                .job(applyCreateRequest.getJob())
                .environment(applyCreateRequest.getEnvironment())
                .people(applyCreateRequest.getPeople())
                .comment(applyCreateRequest.getComment())
                .openTalk(applyCreateRequest.getOpenTalk())
                .region(applyCreateRequest.getRegion())
                .isExperience(applyCreateRequest.getIsExperience())
                .url(applyCreateRequest.getUrl())
                .applyExperiences(ApplyExperience.from(applyCreateRequest.getExperiences()))
                .seller(post.getUser())
                .build();

    }

    public void approve(ApprovalType requestApproval) {
        if (this.approval == ApprovalType.WAITING && requestApproval == ApprovalType.APPROVED) {
            this.approval = ApprovalType.APPROVED;
        }

        if (this.approval == ApprovalType.WAITING && requestApproval == ApprovalType.REJECTED) {
            this.approval = ApprovalType.REJECTED;
        }

    }

    public void cancel() {
        if (this.approval == ApprovalType.WAITING) {
            this.approval = ApprovalType.CANCEL;
        }
    }

    public void modify(ApplyUpdateRequest applyUpdateRequest) {
        this.comment = applyUpdateRequest.getComment();
        this.openTalk = applyUpdateRequest.getOpenTalk();
    }

    public void rejectApply() {
        this.approval = ApprovalType.REJECTED;
    }

    public void cancelApply() {
        this.approval = ApprovalType.CANCEL;
    }
}
