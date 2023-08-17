package com.ddungja.petmily.report.controller;

import com.ddungja.petmily.report.service.ReportService;
import com.ddungja.petmily.user.domain.user.User;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/report")
@Slf4j
public class ReportController {

    private final ReportService reportService;

    @PostMapping("/{postId}")
    @ApiResponse(responseCode = "204", description = "신고하기 성공")
    public ResponseEntity<?> reportPost(@AuthenticationPrincipal User user, @PathVariable Long postId) {
        log.info("신고하기 user = {}, postId = {}", user, postId);
        reportService.reportPost(user.getId(), postId);
        return ResponseEntity.noContent().build();
    }


}
