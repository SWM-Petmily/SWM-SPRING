package com.ddungja.petmily.user.controller;

import com.ddungja.petmily.user.domain.Profile;
import com.ddungja.petmily.user.domain.User;
import com.ddungja.petmily.user.service.ProfileService;
import com.ddungja.petmily.user.controller.response.MyProfileCreateResponse;
import com.ddungja.petmily.user.controller.response.ProfileResponse;
import com.ddungja.petmily.user.controller.response.MyProfileUpdateResponse;
import com.ddungja.petmily.user.domain.request.MyProfileCreateRequest;
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

    @Operation(summary = "내 프로필 등록")
    @PostMapping
    public ResponseEntity<?> create(@AuthenticationPrincipal User user, @Valid @RequestBody MyProfileCreateRequest myProfileCreateRequest) {
        log.info("내 프로필 등록 userId = {}", user.getId());
        Profile profile = profileService.create(myProfileCreateRequest, user.getId());
        return ResponseEntity.ok(MyProfileCreateResponse.from(profile));
    }

    @Operation(summary = "프로필 상세보기")
    @GetMapping("/{userId}")
    public ResponseEntity<?> get(@AuthenticationPrincipal User user, @PathVariable Long userId) {
        log.info("프로필 상세보기 userId = {}", userId);
        if (user != null) {
            if (user.getId().equals(userId)) {
                return ResponseEntity.ok(ProfileResponse.from(profileService.get(user.getId()), true));
            }
        }
        return ResponseEntity.ok(ProfileResponse.from(profileService.get(userId), false));
    }

    @Operation(summary = "내 프로필 수정하기")
    @PutMapping
    public ResponseEntity<?> modify(@AuthenticationPrincipal User user, @RequestBody ProfileUpdateRequest profileUpdateRequest) {
        log.info("내 프로필 수정하기 user = {},  profileUpdateRequest = {}", profileUpdateRequest, user);
        Profile profile = profileService.modify(profileUpdateRequest, user.getId());
        return ResponseEntity.ok(MyProfileUpdateResponse.from(profile));
    }
}
