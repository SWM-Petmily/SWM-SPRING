package com.ddungja.petmily.post.controller;


import com.ddungja.petmily.common.domain.exception.CustomException;
import com.ddungja.petmily.post.controller.response.MainPostResponse;
import com.ddungja.petmily.post.controller.response.MyPostListResponse;
import com.ddungja.petmily.post.controller.response.PostCreateResponse;
import com.ddungja.petmily.post.controller.response.PostGetResponse;
import com.ddungja.petmily.post.domain.Post;
import com.ddungja.petmily.post.domain.request.PostCreateRequest;
import com.ddungja.petmily.post.domain.request.PostFilterRequest;
import com.ddungja.petmily.post.domain.type.PostStatusType;
import com.ddungja.petmily.post.service.PostService;
import com.ddungja.petmily.user.domain.User;
import com.ddungja.petmily.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static com.ddungja.petmily.common.domain.exception.ExceptionCode.USER_NOT_FOUND;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
@Slf4j
public class PostController {

    private final PostService postService;
    private final UserService userService;
    @Operation(summary = "게시글 등록")
    @PostMapping
    public ResponseEntity<?> create(@AuthenticationPrincipal User user, @Valid @RequestBody PostCreateRequest postRequest) {
        if (user == null) throw new CustomException(USER_NOT_FOUND);
        user = userService.get(user.getId());
        log.info("게시글 등록  userId = {} ", user.getId());
        Post post = postService.create(postRequest, user.getId());
        return ResponseEntity.ok(PostCreateResponse.from(post));
    }

    @Operation(summary = "게시글 상세보기")
    @GetMapping("/{postId}")
    public ResponseEntity<?> getSubCategory(@AuthenticationPrincipal User user, @PathVariable Long postId) {
        log.info("게시글 상세보기 postId = {}", postId);
        Post post = postService.get(postId);
        return ResponseEntity.ok(PostGetResponse.from(user, post));
    }

    @Operation(summary = "내가 작성한 게시글 가져오기")
    @GetMapping("/user")
    public ResponseEntity<?> getMyPost(@AuthenticationPrincipal User user, PostStatusType status, Pageable pageable) {
        log.info("내가 작성한 게시글 가져오기 userId = {}", user.getId());
        return ResponseEntity.ok(postService.getMyPost(user.getId(), status, pageable).map(MyPostListResponse::from));
    }

    @Operation(summary = "메인 게시글 가져오기")
    @GetMapping("/main")
    public ResponseEntity<?> getMainPosts(@AuthenticationPrincipal User user, PostFilterRequest postFilterRequest, Pageable pageable) {
        if (user == null) {
            log.info("메인 게시글 가져오기 - 비로그인");
            return ResponseEntity.ok(postService.getMainPosts(postFilterRequest, pageable).map(post -> MainPostResponse.from(post)));

        } else {
            log.info("메인 게시글 가져오기 - 로그인 userId = {}", user.getId());
            return ResponseEntity.ok(postService.getMainPosts(user.getId(), postFilterRequest, pageable).map(post -> MainPostResponse.from(user.getId(), post)));
        }
    }
}

