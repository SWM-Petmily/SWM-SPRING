package com.ddungja.petmily.like.controller;


import com.ddungja.petmily.like.controller.response.LikePostResponse;
import com.ddungja.petmily.like.service.LikeService;
import com.ddungja.petmily.post.domain.type.PostStatusType;
import com.ddungja.petmily.user.domain.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/like")
@Slf4j
public class LikeController {

    private final LikeService likeService;

    @Operation(summary = "좋아요 누른 게시글 불러오기")
    @ApiResponse(responseCode = "200", description = "좋아요 누른 게시글 불러오기 성공", content = @Content(schema = @Schema(implementation = LikePostResponse.class)))
    @GetMapping
    public ResponseEntity<Page<LikePostResponse>> getLike(@AuthenticationPrincipal User user, @RequestParam(value = "status", required = false) PostStatusType postStatusType, Pageable pageable) {
        log.info("좋아요 누른 게시글 불러오기");
        return ResponseEntity.ok(likeService.getLikeList(user.getId(), postStatusType, pageable).map(LikePostResponse::from));
    }

    @Operation(summary = "좋아요 누르기")
    @ApiResponse(responseCode = "204", description = "좋아요 누르기 성공")
    @PostMapping("/{postId}")
    public ResponseEntity<Void> like(@AuthenticationPrincipal User user, @PathVariable Long postId) {
        log.info("좋아요 누르기 postId = {}", postId);
        likeService.like(user.getId(), postId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "좋아요 취소")
    @ApiResponse(responseCode = "204", description = "좋아요 취소 성공")
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> unLike(@AuthenticationPrincipal User user, @PathVariable Long postId) {
        log.info("좋아요 취소 postId = {}", postId);
        likeService.unlike(user.getId(), postId);
        return ResponseEntity.noContent().build();
    }

}


