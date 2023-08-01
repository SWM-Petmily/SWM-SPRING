package com.ddungja.petmily.post.service;

import com.ddungja.petmily.common.domain.exception.CustomException;
import com.ddungja.petmily.post.domain.*;
import com.ddungja.petmily.post.domain.request.DiseaseRequest;
import com.ddungja.petmily.post.domain.request.PostCreateRequest;
import com.ddungja.petmily.post.domain.request.PostFilterRequest;
import com.ddungja.petmily.post.domain.type.ImageType;
import com.ddungja.petmily.post.domain.type.PostStatusType;
import com.ddungja.petmily.post.repository.DiseaseRepository;
import com.ddungja.petmily.post.repository.MainCategoryRepository;
import com.ddungja.petmily.post.repository.PostRepository;
import com.ddungja.petmily.post.repository.SubCategoryRepository;
import com.ddungja.petmily.user.domain.user.User;
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
    public Post create(PostCreateRequest postCreateRequest, Long userId, List<MultipartFile> postImages) throws IOException {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        MainCategory mainCategory = mainCategoryRepository.findByName(postCreateRequest.getMainCategory()).orElseThrow(() -> new CustomException(MAIN_CATEGORY_NOT_FOUND));
        SubCategory subCategory = subCategoryRepository.findByName(postCreateRequest.getSubCategory()).orElseThrow(() -> new CustomException(SUB_CATEGORY_NOT_FOUND));
        Post post = Post.from(postCreateRequest, user, mainCategory, subCategory);
        if(postImages != null && postImages.size() > 0) {
            post.createThumbnailImage(imageService.upload(post, postImages, POST).get(0).getUrl());
            uploadImages(postImages, post, POST);
        }
        uploadDisease(postCreateRequest, post);
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

    private void uploadImages(List<MultipartFile> images, Post post, ImageType imageType) throws IOException {
        /*이미지 업로드*/
        List<Image> uploadImages = new ArrayList<>();

        if(images != null){
            uploadImages.addAll(imageService.upload(post, images, imageType));
            if(imageType == POST) {
                uploadImages.remove(0);
            }
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

    @Transactional
    public Post certifyVaccination(Long postId, Long userId, List<MultipartFile> vaccinationImages) throws IOException {
        Post post = postRepository.findPostById(postId).orElseThrow(() -> new CustomException(POST_NOT_FOUND));
        if (!post.getUser().getId().equals(userId)) {
            throw new CustomException(POST_NOT_FOUND);
        }
        if(vaccinationImages == null || vaccinationImages.size() == 0) {
            throw new CustomException(IMAGE_NOT_FOUND);
        }
        post.certifyVaccination();
        uploadImages(vaccinationImages, post, VACCINATION);
        return post;
    }

    @Transactional
    public Post certifyMedicalCheck(Long postId, Long userId, List<MultipartFile> medicalCheckImages) throws IOException {
        Post post = postRepository.findPostById(postId).orElseThrow(() -> new CustomException(POST_NOT_FOUND));
        if (!post.getUser().getId().equals(userId)) {
            throw new CustomException(POST_NOT_FOUND);
        }
        if(medicalCheckImages == null || medicalCheckImages.size() == 0) {
            throw new CustomException(IMAGE_NOT_FOUND);
        }
        post.certifyMedicalCheck();
        uploadImages(medicalCheckImages, post, MEDICAL_CHECK);
        return post;
    }

    @Transactional
    public Post certifyRegistration(Long userId, Long postId) {
        Post post = postRepository.findPostById(postId).orElseThrow(() -> new CustomException(POST_NOT_FOUND));
        if (!post.getUser().getId().equals(userId)) {
            throw new CustomException(POST_NOT_FOUND);
        }
        post.certifyRegistration();
        return post;
    }
}