package com.ddungja.petmily.apply.controller;

import com.ddungja.petmily.apply.controller.response.*;
import com.ddungja.petmily.apply.domain.Apply;
import com.ddungja.petmily.apply.domain.ApprovalType;
import com.ddungja.petmily.apply.domain.request.ApplyCreateRequest;
import com.ddungja.petmily.apply.domain.request.ApproveRequest;
import com.ddungja.petmily.apply.service.ApplyService;
import com.ddungja.petmily.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/users/apply")
public class ApplyController {
    private final ApplyService applyService;

    @Operation(summary = "내 게시글에 지원한 지원서 목록 보기")
    @GetMapping("/{postId}")
    public ResponseEntity<?> getByPostId(@AuthenticationPrincipal User user, @PathVariable Long postId, Pageable pageable) {
        log.info("내 게시글 지원 받은 목록 보기 userId : {}", user.getId());
        return ResponseEntity.ok(ApplyPostResponse.from(postId, applyService.supportMyPost(user.getId(), postId, pageable).map(ApplySupportResponse::from)));
    }

    @Operation(summary = "내가 지원한 목록 보기")
    @GetMapping
    public ResponseEntity<?> getByUserId(@AuthenticationPrincipal User user, ApprovalType status, Pageable pageable) {
        log.info("내가 지원한 목록 보기 userId : {}", user.getId());
        return ResponseEntity.ok(applyService.getAppliedList(user.getId(), status, pageable).map(ApplyPostListResponse::from));
    }

    @Operation(summary = "지원 상세 보기")
    @GetMapping("/{applyId}/detail")
    public ResponseEntity<?> getDetail(@AuthenticationPrincipal User user, @PathVariable Long applyId) {
        log.info("지원 상세 보기 : userId = {}, applyId = {} ", user.getId(), applyId);
        Apply apply = applyService.getDetailInfo(applyId);
        if (apply.getUser().getId().equals(user.getId())) {
            return ResponseEntity.ok(ApplyDetailResponse.from(apply, true));
        }
        return ResponseEntity.ok(ApplyDetailResponse.from(apply, false));
    }

    @Operation(summary = "지원 승인/거절")
    @PostMapping("/{applyId}/approval")
    public ResponseEntity<?> approve(@AuthenticationPrincipal User user, @PathVariable Long applyId, @Valid @RequestBody ApproveRequest approveRequest) {
        log.info("지원 승인/거절 : userId = {}, applyId = {} , approvalRequest  = {}", user.getId(), applyId, approveRequest);
        return ResponseEntity.ok(ApprovalResponse.from(applyService.approve(user.getId(), applyId, approveRequest)));
    }

    @Operation(summary = "지원 하기")
    @PostMapping("/{postId}")
    public ResponseEntity<?> apply(@AuthenticationPrincipal User user, @PathVariable Long postId, @Valid @RequestBody ApplyCreateRequest applyCreateRequest) {
        log.info("지원 하기 userId = {}, postId = {}", user.getId(), postId);
        return ResponseEntity.status(CREATED).body(ApplyCreateResponse.from(applyService.apply(user.getId(), postId, applyCreateRequest)));
    }

    @Operation(summary = "지원 취소하기")
    @DeleteMapping("/{postId}/cancel")
    public ResponseEntity<?> cancel(@AuthenticationPrincipal User user, @PathVariable Long postId) {
        log.info("지원 취소하기 userId = {}, postId = {}", user.getId(), postId);
        return ResponseEntity.ok().body(ApplyCancelResponse.from(applyService.cancel(user.getId(), postId)));
    }
}
