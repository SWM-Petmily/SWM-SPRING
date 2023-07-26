package com.ddungja.petmily.apply.controller;

import com.ddungja.petmily.apply.controller.response.*;
import com.ddungja.petmily.apply.domain.Apply;
import com.ddungja.petmily.apply.domain.ApprovalType;
import com.ddungja.petmily.apply.domain.request.ApplyCreateRequest;
import com.ddungja.petmily.apply.domain.request.ApplyUpdateResponse;
import com.ddungja.petmily.apply.domain.request.ApproveRequest;
import com.ddungja.petmily.apply.service.ApplyService;
import com.ddungja.petmily.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@Tag(name = "Apply", description = "지원 관련 API")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/users/apply")
public class ApplyController {
    private final ApplyService applyService;

    @Operation(summary = "지원 받은 목록")
    @ApiResponse(responseCode = "200", description = "지원 받은 목록 조회 성공", content = @Content(schema = @Schema(implementation = ApplySupportResponse.class)))
    @GetMapping("/{postId}")
    public ResponseEntity<?> getByPostId(@AuthenticationPrincipal User user, @PathVariable Long postId, Pageable pageable) {
        log.info("지원 받은 목록 userId : {}", user.getId());
        return ResponseEntity.ok(applyService.supportMyPost(user.getId(), postId, pageable).map(ApplySupportResponse::from));
    }

    @Operation(summary = "내가 지원한 게시글 목록 보기")
    @ApiResponse(responseCode = "200", description = "게시글 목록 조회 성공", content = @Content(schema = @Schema(implementation = ApplyPostListResponse.class)))
    @GetMapping
    public ResponseEntity<?> getByUserId(@AuthenticationPrincipal User user, ApprovalType status, Pageable pageable) {
        log.info("내가 지원한 게시글 목록 보기 userId : {}", user.getId());
        return ResponseEntity.ok(applyService.getAppliedList(user.getId(), status, pageable).map(ApplyPostListResponse::from));
    }

    @Operation(summary = "지원 상세보기")
    @ApiResponse(responseCode = "200", description = "지원 상세보기 조회 성공", content = @Content(schema = @Schema(implementation = ApplyDetailResponse.class)))
    @GetMapping("/{applyId}/detail")
    public ResponseEntity<?> getDetail(@AuthenticationPrincipal User user, @PathVariable Long applyId) {
        log.info("지원 상세보기 : userId = {}, applyId = {} ", user.getId(), applyId);
        Apply apply = applyService.getDetailInfo(applyId);
        if (apply.getUser().getId().equals(user.getId())) {
            return ResponseEntity.ok(ApplyDetailResponse.from(apply, true));
        }
        return ResponseEntity.ok(ApplyDetailResponse.from(apply, false));
    }

    @Operation(summary = "지원 수락/거절")
    @ApiResponse(responseCode = "200", description = "지원 수락/거절 성공", content = @Content(schema = @Schema(implementation = ApprovalResponse.class)))
    @PostMapping("/{applyId}/approval")
    public ResponseEntity<?> approve(@AuthenticationPrincipal User user, @PathVariable Long applyId, @Valid @RequestBody ApproveRequest approveRequest) {
        log.info("지원 수락/거절 : userId = {}, applyId = {} , approvalRequest  = {}", user.getId(), applyId, approveRequest);
        return ResponseEntity.ok(ApprovalResponse.from(applyService.approve(user.getId(), applyId, approveRequest)));
    }

    @Operation(summary = "지원하기")
    @ApiResponse(responseCode = "201", description = "지원하기 성공", content = @Content(schema = @Schema(implementation = ApplyCreateResponse.class)))
    @PostMapping("/{postId}")
    public ResponseEntity<?> apply(@AuthenticationPrincipal User user, @PathVariable Long postId, @Valid @RequestBody ApplyCreateRequest applyCreateRequest) {
        log.info("지원하기 userId = {}, postId = {}", user.getId(), postId);
        return ResponseEntity.status(CREATED).body(ApplyCreateResponse.from(applyService.apply(user.getId(), postId, applyCreateRequest)));
    }

    @Operation(summary = "지원취소")
    @ApiResponse(responseCode = "200", description = "지원취소 성공", content = @Content(schema = @Schema(implementation = ApplyCancelResponse.class)))
    @DeleteMapping("/{postId}/cancel")
    public ResponseEntity<?> cancel(@AuthenticationPrincipal User user, @PathVariable Long postId) {
        log.info("지원취소 userId = {}, postId = {}", user.getId(), postId);
        return ResponseEntity.ok().body(ApplyCancelResponse.from(applyService.cancel(user.getId(), postId)));
    }

    @Operation(summary = "지원수정")
    @ApiResponse(responseCode = "200", description = "지원수정 성공", content = @Content(schema = @Schema(implementation = ApplyUpdateResponse.class)))
    @PutMapping("/{applyId}")
    public ResponseEntity<?> modify(@AuthenticationPrincipal User user, @PathVariable Long applyId, @RequestBody ApplyUpdateRequest applyUpdateRequest) {
        log.info("지원수정 userId = {}, applyId = {}", user.getId(), applyId);
        return ResponseEntity.ok().body(ApplyUpdateResponse.from(applyService.modify(user.getId(), applyId, applyUpdateRequest)));
    }
}
