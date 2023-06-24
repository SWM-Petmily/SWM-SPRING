package com.ddungja.app.posts.personality.infrastructure;

import com.ddungja.app.common.domain.BaseTimeEntity;
import com.ddungja.app.posts.post.infrastructure.PostEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "personalities")
public class PersonalityEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personality_id")
    private Long id;

    @JoinColumn(name = "post_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private PostEntity post;

    private String personality;

    private Type type;

    @Builder
    private PersonalityEntity(Long id, PostEntity post, String personality, Type type) {
        this.id = id;
        this.post = post;
        this.personality = personality;
        this.type = type;
    }


}

