package com.ddungja.petmily.post.controller;


import com.ddungja.petmily.post.controller.response.PostCreateResponse;
import com.ddungja.petmily.post.controller.response.PostGetResponse;
import com.ddungja.petmily.post.domain.image.Image;
import com.ddungja.petmily.post.domain.post.Post;
import com.ddungja.petmily.post.domain.request.PostCreateRequest;
import com.ddungja.petmily.post.service.ImageService;
import com.ddungja.petmily.post.service.PostService;
import com.ddungja.petmily.users.user.domain.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
@Slf4j
public class PostController {

    private final PostService postService;
    private final ImageService imageService;

    @PostMapping
    public ResponseEntity<?> create(@AuthenticationPrincipal User user, @Valid @RequestBody PostCreateRequest postRequest) {
        if(user==null) { // 로그인 연동 전 임의의 값 확인
            log.info("포스트 생성 요청 userId = {}", 1);
            Post post = postService.create(postRequest, 1L);
            return ResponseEntity.ok(PostCreateResponse.from(post));
        }else{
            log.info("포스트 생성 요청 userId = {} ", user.getId());
            Post post = postService.create(postRequest, user.getId());
            return ResponseEntity.ok(PostCreateResponse.from(post));
        }
    }
    @Transactional
    @GetMapping("/{postId}")
    public ResponseEntity<?> getSubCategory(@AuthenticationPrincipal User user,@PathVariable Long postId){
        log.info("포스트 내용 불러오기 postId = {}", postId);
        Post post = postService.get(postId);
        List<Image> images = imageService.getImages(postId);
        return ResponseEntity.ok(PostGetResponse.from(user,post,images));
    }}
