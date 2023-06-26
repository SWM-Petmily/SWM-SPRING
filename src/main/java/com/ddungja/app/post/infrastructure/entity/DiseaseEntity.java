package com.ddungja.app.post.infrastructure.entity;

import com.ddungja.app.common.domain.BaseTimeEntity;
import com.ddungja.app.post.domain.Disease;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "diseases")
public class DiseaseEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "disease_id")
    private Long id;

    @JoinColumn(name = "post_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private PostEntity post;


    @Builder
    private DiseaseEntity(Long id, PostEntity post, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.post = post;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public static DiseaseEntity from(Disease disease) {
        return DiseaseEntity.builder()
                .id(disease.getId())
                .post(disease.getPost())
                .createDate(disease.getCreateDate())
                .updateDate(disease.getUpdateDate())
                .build();
    }

    public Disease toDomain() {
        return Disease.builder()
                .id(id)
                .post(post)
                .createDate(createDate)
                .updateDate(updateDate)
                .build();
    }



}
