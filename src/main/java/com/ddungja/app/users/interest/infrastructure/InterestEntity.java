package com.ddungja.app.users.interest.infrastructure;

import com.ddungja.app.common.domain.BaseTimeEntity;
import com.ddungja.app.post.infrastructure.entity.PostEntity;
import com.ddungja.app.users.interest.domain.Interest;
import com.ddungja.app.users.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "interests")
public class InterestEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interest_id")
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "post_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private PostEntity post;


    @Builder
    private InterestEntity(Long id, User user, PostEntity post, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.user = user;
        this.post = post;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public static InterestEntity from(Interest interest) {
        return InterestEntity.builder()
                .user(interest.getUser())
                .post(interest.getPost())
                .build();
    }

    public Interest toDomain() {
        return Interest.builder()
                .id(id)
                .user(user)
                .post(post)
                .createDate(createDate)
                .updateDate(updateDate)
                .build();
    }


}
