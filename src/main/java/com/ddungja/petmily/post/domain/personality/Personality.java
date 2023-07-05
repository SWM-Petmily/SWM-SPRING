package com.ddungja.petmily.post.domain.personality;

import com.ddungja.petmily.common.domain.BaseTimeEntity;
import com.ddungja.petmily.post.domain.post.Post;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "personalities")
public class Personality extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personality_id")
    private Long id;

    @JoinColumn(name = "post_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    private String personality;

    private PersonalityType type;

    @Builder
    private Personality(Long id, Post post, String personality, PersonalityType type, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.post = post;
        this.personality = personality;
        this.type = type;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }



}

