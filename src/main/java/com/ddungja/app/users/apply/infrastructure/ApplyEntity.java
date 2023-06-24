package com.ddungja.app.users.apply.infrastructure;


import com.ddungja.app.common.domain.BaseTimeEntity;
import com.ddungja.app.posts.post.infrastructure.PostEntity;
import com.ddungja.app.users.apply.domain.Apply;
import com.ddungja.app.users.apply.domain.Approval;
import com.ddungja.app.users.user.infrastructure.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "applys")
public class ApplyEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apply_id")
    private Long id;


    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;

    @JoinColumn(name = "seller_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity seller;

    @JoinColumn(name = "post_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private PostEntity post;

    @Enumerated(EnumType.STRING)
    private Approval approval;

    @Builder
    private ApplyEntity(Long id, UserEntity user, UserEntity seller, PostEntity post, Approval approval, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.user = user;
        this.seller = seller;
        this.post = post;
        this.approval = approval;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public static ApplyEntity from(Apply apply) {
        return ApplyEntity.builder()
                .user(apply.getUser())
                .seller(apply.getSeller())
                .post(apply.getPost())
                .approval(apply.getApproval())
                .build();
    }

    public Apply toDomain() {
        return Apply.builder()
                .id(id)
                .user(user)
                .seller(seller)
                .post(post)
                .approval(approval)
                .createDate(createDate)
                .updateDate(updateDate)
                .build();
    }
}
