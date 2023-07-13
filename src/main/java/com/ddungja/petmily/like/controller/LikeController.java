package com.ddungja.petmily.like.controller;


import com.ddungja.petmily.common.domain.exception.CustomException;
import com.ddungja.petmily.like.controller.response.LikeCreateResponse;
import com.ddungja.petmily.like.controller.response.LikeListResponse;
import com.ddungja.petmily.like.domain.Like;
import com.ddungja.petmily.like.service.LikeService;
import com.ddungja.petmily.post.domain.post.Post;
import com.ddungja.petmily.post.service.PostService;
import com.ddungja.petmily.user.domain.User;
import com.ddungja.petmily.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ddungja.petmily.common.domain.exception.ExceptionCode.USER_NOT_FOUND;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/like")
@Slf4j
public class LikeController {

    private final LikeService likeService;
    private final PostService postService;

    private final UserService userService;


    @GetMapping("/")
    public ResponseEntity<?> getLike(@AuthenticationPrincipal User user){
        log.info("좋아요 누른 게시글 불러오기");
        if(user== null) throw new CustomException(USER_NOT_FOUND);
        user = userService.get(user.getId());
        List<Like> likes = likeService.getLikeList(user.getId());
        return ResponseEntity.ok(LikeListResponse.from(likes));
    }

    @PostMapping("/{postId}")
    public ResponseEntity<?> Like(@AuthenticationPrincipal User user, @PathVariable Long postId){
        log.info("좋아요 누르기 postId = {}", postId);
        if(user== null) throw new CustomException(USER_NOT_FOUND);
        user = userService.get(user.getId());
        Post post = postService.get(postId);
        User loginUser = userService.get(user.getId());
        Like like = likeService.like(loginUser, post);
        return ResponseEntity.ok(LikeCreateResponse.from(like));

    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> UnLike(@AuthenticationPrincipal User user, @PathVariable Long postId){
        log.info("좋아요 취소 postId = {}", postId);
        if(user== null) throw new CustomException(USER_NOT_FOUND);
        user = userService.get(user.getId());
        Like like = likeService.getLike(postId, user.getId());
        likeService.unlike(like);
        return ResponseEntity.ok(LikeCreateResponse.from(like));

    }

}


