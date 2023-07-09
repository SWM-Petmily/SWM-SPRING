package com.ddungja.petmily.post.controller;


import com.ddungja.petmily.post.controller.response.PostCreateResponse;
import com.ddungja.petmily.post.domain.post.Post;
import com.ddungja.petmily.post.domain.request.PostCreateRequest;
import com.ddungja.petmily.post.service.PostService;
import com.ddungja.petmily.users.user.domain.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
@Slf4j
public class PostController {

    private final PostService postService;

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

    @GetMapping("/{postId}")
    public ResponseEntity<?> getSubCategory(@PathVariable Long postId){
        log.info("포스트 내용 불러오기 postId = {}", postId);
        return ResponseEntity.ok(PostCreateResponse.from(postService.get(postId)));
    }}
