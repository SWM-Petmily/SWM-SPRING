package com.ddungja.petmily.user.controller;

import com.ddungja.petmily.user.domain.Profile;
import com.ddungja.petmily.user.domain.User;
import com.ddungja.petmily.user.service.ProfileService;
import com.ddungja.petmily.user.controller.response.ProfileCreateResponse;
import com.ddungja.petmily.user.controller.response.ProfileResponse;
import com.ddungja.petmily.user.controller.response.ProfileUpdateResponse;
import com.ddungja.petmily.user.domain.request.ProfileCreateRequest;
import com.ddungja.petmily.user.domain.request.ProfileUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users/profile")
@Slf4j
public class ProfileController {

    private final ProfileService profileService;
    
    @Operation(summary = "프로필 생성하기")
    @PostMapping
    public ResponseEntity<?> create(@AuthenticationPrincipal User user, @Valid @RequestBody ProfileCreateRequest profileCreateRequest) {
        log.info("프로필 생성 요청 userId = {}", user.getId());
        Profile profile = profileService.create(profileCreateRequest, user.getId());
        return ResponseEntity.ok(ProfileCreateResponse.from(profile));
    }

    @Operation(summary = "프로필 상세보기")
    @GetMapping("/{userId}")
    public ResponseEntity<?> get(@PathVariable Long userId) {
        log.info("프로필 상세보기  userId = {}", userId);
        return ResponseEntity.ok(ProfileResponse.from(profileService.get(userId)));
    }

    @Operation(summary = "내 프로필 가져오기")
    @GetMapping
    public ResponseEntity<?> getMyProfile(@AuthenticationPrincipal User user) {
        log.info("내 프로필 가져오기 userId = {}", user.getId());
        return ResponseEntity.ok(ProfileResponse.from(profileService.get(user.getId())));
    }

    @Operation(summary = "프로필 수정하기")
    @PutMapping
    public ResponseEntity<?> update(@AuthenticationPrincipal User user, @Valid @RequestBody ProfileUpdateRequest profileUpdateRequest) {
        log.info("프로필 수정하기  userId = {}", user.getId());
        Profile profile = profileService.update(profileUpdateRequest, user.getId());
        return ResponseEntity.ok(ProfileUpdateResponse.from(profile));
    }
}
