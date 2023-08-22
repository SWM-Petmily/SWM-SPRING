package com.ddungja.petmily.post.service;

import com.ddungja.petmily.post.domain.Disease;
import com.ddungja.petmily.post.domain.Post;
import com.ddungja.petmily.post.domain.request.DiseaseRequest;
import com.ddungja.petmily.post.domain.request.PostCreateRequest;
import com.ddungja.petmily.post.repository.DiseaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class DiseaseService {

    private final DiseaseRepository diseaseRepository;

    @Transactional
    public void uploadDisease(PostCreateRequest postCreateRequest, Post post) {
        if (!postCreateRequest.getDiseases().isEmpty()) {
            for (DiseaseRequest disease : postCreateRequest.getDiseases()) {
                Disease uploadDisease = Disease.builder()
                        .post(post)
                        .name(disease.getName())
                        .build();
                diseaseRepository.save(uploadDisease);
            }
        }
    }
}


