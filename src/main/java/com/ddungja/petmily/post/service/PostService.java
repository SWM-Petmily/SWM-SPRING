package com.ddungja.petmily.post.service;

import com.ddungja.petmily.common.domain.exception.CustomException;
import com.ddungja.petmily.post.domain.*;
import com.ddungja.petmily.post.domain.request.DiseaseRequest;
import com.ddungja.petmily.post.domain.request.PostCreateRequest;
import com.ddungja.petmily.post.domain.request.PostFilterRequest;
import com.ddungja.petmily.post.domain.type.PostStatusType;
import com.ddungja.petmily.post.repository.*;
import com.ddungja.petmily.user.domain.User;
import com.ddungja.petmily.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.ddungja.petmily.common.domain.exception.ExceptionCode.*;
import static com.ddungja.petmily.post.domain.type.ImageType.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final UserRepository userRepository;

    private final PostRepository postRepository;
    private final MainCategoryRepository mainCategoryRepository;
    private final SubCategoryRepository subCategoryRepository;

    private final DiseaseRepository diseaseRepository;
    private final ImageService imageService;


    /*포스트 업로드*/
    @Transactional
    public Post create(PostCreateRequest postCreateRequest, Long id, List<MultipartFile> postImages, List<MultipartFile> vaccinationImages, List<MultipartFile> medicalCheckImages) throws IOException {
        User user = userRepository.findById(id).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        MainCategory mainCategory = mainCategoryRepository.findById(postCreateRequest.getMainCategory()).orElseThrow(() -> new CustomException(MAIN_CATEGORY_NOT_FOUND));
        SubCategory subCategory = subCategoryRepository.findById(postCreateRequest.getSubCategory()).orElseThrow(() -> new CustomException(SUB_CATEGORY_NOT_FOUND));
        Post post = Post.from(postCreateRequest, user, mainCategory, subCategory);
        uploadDisease(postCreateRequest, post);
        uploadImages(postImages, vaccinationImages, medicalCheckImages, post);
        return postRepository.save(post);
    }

    private void uploadDisease(PostCreateRequest postCreateRequest, Post post) {
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
    }

    private void uploadImages(List<MultipartFile> postImages, List<MultipartFile> vaccinationImages, List<MultipartFile> medicalCheckImages, Post post) throws IOException {
        /*이미지 업로드*/
        List<Image> uploadImages = new ArrayList<>();
        if (postImages != null) {
            uploadImages.addAll(imageService.upload(post, postImages, POST));
            post.createThumbnailImage(uploadImages.get(0).getUrl());
        }

        /*예방접종 인증 - 사진만*/
        if (vaccinationImages != null) {
            uploadImages.addAll(imageService.upload(post, vaccinationImages, VACCINATION));
        }

        /*건강검진 정보 인증 - 사진만*/
        if (medicalCheckImages != null) {
            uploadImages.addAll(imageService.upload(post, medicalCheckImages, MEDICAL_CHECK));
        }
        post.uploadImages(uploadImages);
    }

    /*포스트 보기*/

    public Post get(Long id) {
        return postRepository.findPostById(id).orElseThrow(() -> new CustomException(POST_NOT_FOUND));
    }

    public Page<Post> getMyPost(Long userId, PostStatusType postStatusType, Pageable pageable) {
        userRepository.findById(userId).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        return postRepository.getMyPost(userId, postStatusType, pageable);
    }

    public Page<Post> getMainPosts(Long userId, PostFilterRequest postFilterRequest, Pageable pageable) {
        userRepository.findById(userId).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        return postRepository.getMainPosts(userId, postFilterRequest, pageable);
    }

    public Page<Post> getMainPosts(PostFilterRequest postFilterRequest, Pageable pageable) {
        return postRepository.getMainPosts(postFilterRequest, pageable);
    }
}