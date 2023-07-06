package com.ddungja.petmily.apply.controller;

import com.ddungja.petmily.apply.controller.response.ApplyPostResponse;
import com.ddungja.petmily.apply.controller.response.ApplySupportResponse;
import com.ddungja.petmily.apply.domain.Apply;
import com.ddungja.petmily.apply.service.ApplyService;
import com.ddungja.petmily.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/users/apply")
public class ApplyController {
    private final ApplyService applyService;
    @Operation(summary = "내 게시글 지원 받은 목록 보기")
    @GetMapping("/{postId}")
    public ResponseEntity<?> getByPostId(@AuthenticationPrincipal User user, @PathVariable Long postId, Pageable pageable) {
        log.info("내 게시글 지원 받은 목록 보기 userId : {}", user.getId());
        return ResponseEntity.ok( ApplyPostResponse.from(postId, applyService.getByPostId(user.getId(), postId, pageable).map(ApplySupportResponse::from)));
    }
    @Operation(summary = "내가 지원한 게시글 목록 목록 보기")
    @GetMapping
    public ResponseEntity<?> getByUserId(@AuthenticationPrincipal User user, Pageable pageable) {
        log.info("내가 지원한 목록 보기 userId : {}", user.getId());

        return ResponseEntity.ok(applyService.getByUserId(user.getId(), pageable).map(ApplyPostListResponse::from));
    }
    @Operation(summary = "지원 상세 보기")
    @GetMapping("/detail/{applyId}")
    public ResponseEntity<?> getDetail(@AuthenticationPrincipal User user, @PathVariable Long applyId) {
        Apply apply = applyService.findById(applyId);
        if (apply.getUser().getId().equals(user.getId())) {
            return ResponseEntity.ok(ApplyDetailResponse.from(apply, true));
        }
        return ResponseEntity.ok(ApplyDetailResponse.from(apply, false));
    }

}
