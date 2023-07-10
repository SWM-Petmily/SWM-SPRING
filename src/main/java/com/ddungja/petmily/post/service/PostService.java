package com.ddungja.petmily.post.service;

import com.ddungja.petmily.common.domain.exception.CustomException;
import com.ddungja.petmily.post.domain.request.DiseaseRequest;
import com.ddungja.petmily.post.domain.request.ImageCreateRequest;
import com.ddungja.petmily.post.domain.request.PostCreateRequest;
import com.ddungja.petmily.post.domain.Disease;
import com.ddungja.petmily.post.domain.MainCategory;
import com.ddungja.petmily.post.domain.SubCategory;
import com.ddungja.petmily.post.domain.image.Image;
import com.ddungja.petmily.post.domain.image.ImageType;
import com.ddungja.petmily.post.domain.post.Post;
import com.ddungja.petmily.post.repository.*;
import com.ddungja.petmily.user.domain.User;
import com.ddungja.petmily.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ddungja.petmily.common.domain.exception.ExceptionCode.*;
import static com.ddungja.petmily.post.domain.image.ImageType.MEDICAL_CHECK;
import static com.ddungja.petmily.post.domain.image.ImageType.POST;

@Service
@RequiredArgsConstructor
public class PostService {

    private final UserRepository userRepository;

    private final PostRepository postRepository;

    private final MainCategoryRepository mainCategoryRepository;
    private final SubCategoryRepository subCategoryRepository;

    private final ImageRepository imageRepository;
    private final DiseaseRepository diseaseRepository;


    /*포스트 업로드*/
    @Transactional
    public Post create(PostCreateRequest postCreateRequest, Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new CustomException(USER_NOT_FOUND));

        MainCategory mainCategory = mainCategoryRepository.findById(postCreateRequest.getMainCategory()).orElseThrow(() -> new CustomException(MAIN_CATEGORY_NOT_FOUND));
        SubCategory subCategory = subCategoryRepository.findById(postCreateRequest.getSubCategory()).orElseThrow(() -> new CustomException(SUB_CATEGORY_NOT_FOUND));

        Post post = postCreateRequest.toEntity(user, mainCategory, subCategory);

        /*질병 업로드*/
        if (postCreateRequest.getDiseases() != null) {
            for (DiseaseRequest disease : postCreateRequest.getDiseases()) {
                Disease uploadDisease = Disease.builder()
                        .post(post)
                        .name(disease.getName())
                        .build();
                diseaseRepository.save(uploadDisease);
            }
        }

        /*이미지 업로드*/
        if (postCreateRequest.getPostImages() != null) {
            post.setThumbnailImage(postCreateRequest.getPostImages().get(0).getUrl());
            for (ImageCreateRequest image : postCreateRequest.getPostImages()) {
                Image uploadimage = Image.builder()
                        .post(post)
                        .url(image.getUrl())
                        .imageType(POST)
                        .build();
                imageRepository.save(uploadimage);
            }
        }

        /*예방접종 인증 - 사진만*/
        if (postCreateRequest.getVaccinationImages() != null) {
            for (ImageCreateRequest image : postCreateRequest.getVaccinationImages()) {
                Image uploadimage = Image.builder()
                        .post(post)
                        .url(image.getUrl())
                        .imageType(ImageType.VACCINATION)
                        .build();
                imageRepository.save(uploadimage);
            }
        }

        /*건강검진 정보 인증 - 사진만*/
        if (postCreateRequest.getMedicalCheckImages() != null) {
            for (ImageCreateRequest image : postCreateRequest.getMedicalCheckImages()) {
                Image uploadimage = Image.builder()
                        .post(post)
                        .url(image.getUrl())
                        .imageType(MEDICAL_CHECK)
                        .build();
                imageRepository.save(uploadimage);
            }
        }

        postRepository.save(post);

        return post;
    }

    /*포스트 보기*/

    @Transactional
    public Post get(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new CustomException(POST_NOT_FOUND));
    }

    public Page<Post> getMyPost(Long userId, Pageable pageable) {
        userRepository.findById(userId).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        return postRepository.findByUserId(userId, pageable);
    }
}