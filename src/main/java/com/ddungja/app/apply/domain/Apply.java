package com.ddungja.app.apply.domain;


import com.ddungja.app.common.domain.BaseTimeEntity;
import com.ddungja.app.post.domain.post.Post;
import com.ddungja.app.users.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "applys")
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

    private String job;
    private String environment;
    private int people;
    private String comment;
    private String openTalk;
    private String region;
    private boolean isExperience;
    private String url;

    @Builder
    private Apply(Long id, User user, User seller, Post post, ApprovalType approval, String job, String environment, int people, String comment, String openTalk, String region, boolean isExperience, String url) {
        this.id = id;
        this.user = user;
        this.seller = seller;
        this.post = post;
        this.approval = approval;
        this.job = job;
        this.environment = environment;
        this.people = people;
        this.comment = comment;
        this.openTalk = openTalk;
        this.region = region;
        this.isExperience = isExperience;
        this.url = url;
    }
}
