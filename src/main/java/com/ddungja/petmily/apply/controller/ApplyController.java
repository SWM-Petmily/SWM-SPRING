package com.ddungja.petmily.apply.controller;

import com.ddungja.petmily.apply.service.ApplyService;
import com.ddungja.petmily.apply.service.ApplyExperienceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/users/apply")
public class ApplyController {
    private final ApplyService applyService;
    private final ApplyExperienceService applyExperienceService;


//    @Operation(summary = "지원 받은 목록 보기")
//    @GetMapping("/{postId}")
//    public ResponseEntity<?> getByPostId(@AuthenticationPrincipal User user, @PathVariable Long postId){
//        applyService.getByPostId(user.getId(), postId);
//
//        return ResponseEntity.ok();
//    }
//
//
//    @Operation(summary = "내가 지원한 목록 보기")
//    @GetMapping
//    public ResponseEntity<?> getByUserId(@AuthenticationPrincipal User user){
//
//        return ResponseEntity.ok();
//    }
//
//    @Operation(summary = "지원 상세 보기")
//    @GetMapping("/{applyId}")
//    public ResponseEntity<?> getDetail(@AuthenticationPrincipal User user, @PathVariable Long applyId){
//
//        return ResponseEntity.ok();
//    }

}
