package com.ddungja.petmily.report.domain;

import com.ddungja.petmily.post.domain.Post;
import com.ddungja.petmily.user.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long id;

    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "post_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @Builder
    public Report(Long id, User user, Post post) {
        this.id = id;
        this.user = user;
        this.post = post;
    }

    public static Report from(User user, Post post){
        return Report.builder()
                .user(user)
                .post(post)
                .build();
    }
}
