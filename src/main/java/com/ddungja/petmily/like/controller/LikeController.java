package com.ddungja.petmily.like.controller;


import com.ddungja.petmily.like.controller.response.LikeCreateResponse;
import com.ddungja.petmily.like.controller.response.LikePostResponse;
import com.ddungja.petmily.like.domain.Like;
import com.ddungja.petmily.like.service.LikeService;
import com.ddungja.petmily.post.domain.type.PostStatusType;
import com.ddungja.petmily.post.service.PostService;
import com.ddungja.petmily.user.domain.User;
import com.ddungja.petmily.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/like")
@Slf4j
public class LikeController {

    private final LikeService likeService;

    @GetMapping
    public ResponseEntity<?> getLike(@AuthenticationPrincipal User user, @RequestParam(value = "status") PostStatusType postStatusType, Pageable pageable){
        log.info("좋아요 누른 게시글 불러오기");
        Page<Like> likes = likeService.getLikeList(user.getId(),postStatusType, pageable);
        return ResponseEntity.ok(likes.map(LikePostResponse::from));
    }

    @PostMapping("/{postId}")
    public ResponseEntity<?> Like(@AuthenticationPrincipal User user, @PathVariable Long postId){
        log.info("좋아요 누르기 postId = {}", postId);
        Like like = likeService.like(user.getId(), postId);
        return ResponseEntity.ok(LikeCreateResponse.from(like));

    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> UnLike(@AuthenticationPrincipal User user, @PathVariable Long postId){
        log.info("좋아요 취소 postId = {}", postId);
        likeService.unlike(user.getId(), postId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}


