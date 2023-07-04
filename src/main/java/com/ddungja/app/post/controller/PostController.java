package com.ddungja.app.post.controller;


import com.ddungja.app.post.controller.response.PostDto;
import com.ddungja.app.post.domain.post.Post;
import com.ddungja.app.post.service.CategoryService;
import com.ddungja.app.post.service.PostService;
import com.ddungja.app.users.user.controller.response.ProfileCreateResponse;
import com.ddungja.app.users.user.domain.Profile;
import com.ddungja.app.users.user.domain.User;
import com.ddungja.app.users.user.domain.request.ProfileCreateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            postService.create(postRequest, 1L);
            return ResponseEntity.ok(postRequest);
        }else{
            log.info("포스트 생성 요청 userId = {}", user.getId());
            postService.create(postRequest, user.getId());
            return ResponseEntity.ok(postRequest);
        }
    }
}
