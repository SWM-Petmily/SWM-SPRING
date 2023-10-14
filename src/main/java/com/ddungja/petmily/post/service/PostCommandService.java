package com.ddungja.petmily.post.service;


import com.ddungja.petmily.apply.domain.Apply;
import com.ddungja.petmily.apply.domain.ApprovalType;
import com.ddungja.petmily.apply.service.port.ApplyRepository;
import com.ddungja.petmily.common.exception.CustomException;
import com.ddungja.petmily.post.domain.Image;
import com.ddungja.petmily.post.domain.MainCategory;
import com.ddungja.petmily.post.domain.Post;
import com.ddungja.petmily.post.domain.SubCategory;
import com.ddungja.petmily.post.domain.request.PostCreateRequest;
import com.ddungja.petmily.post.domain.type.ImageType;
import com.ddungja.petmily.post.repository.MainCategoryRepository;
import com.ddungja.petmily.post.repository.SubCategoryRepository;
import com.ddungja.petmily.post.service.port.PostRepository;
import com.ddungja.petmily.user.domain.user.User;
import com.ddungja.petmily.user.service.port.UserRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.ddungja.petmily.common.exception.ExceptionCode.*;
import static com.ddungja.petmily.post.domain.type.ImageType.*;

@Service
@RequiredArgsConstructor
@Transactional
@Builder
public class PostCommandService {

    private final UserRepository userRepository;
    private final ApplyRepository applyRepository;
    private final PostRepository postRepository;
    private final MainCategoryRepository mainCategoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final ImageService imageService;
    private final DiseaseService diseaseService;

    @Transactional
    public Post create(PostCreateRequest postCreateRequest, Long userId, List<MultipartFile> postImages) throws IOException {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        MainCategory mainCategory = mainCategoryRepository.findByName(postCreateRequest.getMainCategory()).orElseThrow(() -> new CustomException(MAIN_CATEGORY_NOT_FOUND));
        SubCategory subCategory = subCategoryRepository.findByName(postCreateRequest.getSubCategory()).orElseThrow(() -> new CustomException(SUB_CATEGORY_NOT_FOUND));
        Post post = Post.from(postCreateRequest, user, mainCategory, subCategory);
        if (postImages != null && !postImages.isEmpty()) {
            post.createThumbnailImage(imageService.upload(post, postImages, POST));
        }
        diseaseService.uploadDisease(postCreateRequest, post);
        return postRepository.save(post);
    }

    @Transactional
    public Post certifyCheck(Long postId, Long userId, List<MultipartFile> images, ImageType imageType) throws IOException {
        if (!userRepository.existsById(userId)) throw new CustomException(USER_NOT_FOUND);
        Post post = postRepository.findPostById(postId).orElseThrow(() -> new CustomException(POST_NOT_FOUND));
        if (images == null || images.isEmpty()) throw new CustomException(IMAGE_NOT_FOUND);
        if (imageType == VACCINATION) post.certifyVaccination();
        if (imageType == MEDICAL_CHECK) post.certifyMedicalCheck();
        uploadImages(images, post, imageType);
        return post;
    }

    private void uploadImages(List<MultipartFile> images, Post post, ImageType imageType) throws IOException {
        List<Image> uploadImages = new ArrayList<>();
        if (!images.isEmpty()) {
            uploadImages.addAll(imageService.upload(post, images, imageType));
        }
        post.uploadImages(uploadImages);
    }


    @Transactional
    public Post certifyRegistration(Long userId, Long postId) {
        if (!userRepository.existsById(userId)) throw new CustomException(USER_NOT_FOUND);
        Post post = postRepository.findPostById(postId).orElseThrow(() -> new CustomException(POST_NOT_FOUND));
        post.certifyRegistration(userId);
        return post;
    }

    @Transactional
    public void delete(Long userId, Long postId) {
        if (!userRepository.existsById(userId)) throw new CustomException(USER_NOT_FOUND);
        Post post = postRepository.findPostById(postId).orElseThrow(() -> new CustomException(POST_NOT_FOUND));
        post.deletePost(userId);
        applyRepository.findByPostIdAndApproval(postId, ApprovalType.WAITING).forEach(Apply::cancelApply);
    }

    @Transactional
    public void complete(Long userId, Long postId) {
        if (!userRepository.existsById(userId)) throw new CustomException(USER_NOT_FOUND);
        Post post = postRepository.findPostById(postId).orElseThrow(() -> new CustomException(POST_NOT_FOUND));
        post.complete(userId);
        applyRepository.findByPostIdAndApproval(postId, ApprovalType.WAITING).forEach(Apply::rejectApply);

    }

    @Transactional
    public void deleteByUserId(Long userId) {
        List<Post> posts = postRepository.findByUserId(userId);
        for (Post post : posts) {
            post.userDeletePost();
        }
//        postRepository.deleteByUserId(userId);
    }
}