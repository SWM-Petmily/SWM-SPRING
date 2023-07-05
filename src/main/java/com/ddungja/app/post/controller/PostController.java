package com.ddungja.app.post.controller;


import com.ddungja.app.post.controller.response.PostResponse;
import com.ddungja.app.post.domain.post.Post;
import com.ddungja.app.post.domain.request.PostCreateRequest;
import com.ddungja.app.post.service.PostService;
import com.ddungja.app.users.user.domain.User;
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
    public ResponseEntity<?> create(@AuthenticationPrincipal User user, @Valid @RequestBody PostDto.PostCreateRequest postRequest) {
        if(user==null) { // 로그인 연동 전 임의의 값 확인
            log.info("포스트 생성 요청 userId = {}", 1);
            Post post = postService.create(postRequest, 1L);
            return ResponseEntity.ok(PostResponse.from(post));
        }else{
            log.info("포스트 생성 요청 userId = {}", user.getId());
            postService.create(postRequest, user.getId());
            return ResponseEntity.ok(postRequest);
        }
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> getSubCategory(@PathVariable Long postId){
        log.info("포스트 내용 불러오기 postId = {}", postId);
        return ResponseEntity.ok(PostResponse.from(postService.get(postId)));
    }}
