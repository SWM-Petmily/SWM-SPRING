package com.ddungja.petmily.report.controller;

import com.ddungja.petmily.report.service.ReportService;
import com.ddungja.petmily.user.domain.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Report", description = "신고하기 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/reports")
@Slf4j
public class ReportController {

    private final ReportService reportService;

    @Operation(summary = "게시글 신고하기")
    @ApiResponse(responseCode = "204", description = "게시글 신고하기 성공")
    @PostMapping("/posts/{postId}")
    public ResponseEntity<Void> reportPost(@AuthenticationPrincipal User user, @PathVariable Long postId) {
        log.info("신고하기 userId = {}, postId = {}", user.getId(), postId);
        reportService.reportPost(user.getId(), postId);
        return ResponseEntity.noContent().build();
    }
}
