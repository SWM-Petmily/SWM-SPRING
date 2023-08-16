package com.ddungja.petmily.user.controller;

import com.ddungja.petmily.user.controller.response.MyProfileCreateResponse;
import com.ddungja.petmily.user.controller.response.MyProfileUpdateResponse;
import com.ddungja.petmily.user.controller.response.ProfileResponse;
import com.ddungja.petmily.user.domain.profile.Profile;
import com.ddungja.petmily.user.domain.user.User;
import com.ddungja.petmily.user.domain.request.MyProfileCreateRequest;
import com.ddungja.petmily.user.domain.request.ProfileUpdateRequest;
import com.ddungja.petmily.user.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    @ApiResponse(responseCode = "201", description = "내 프로필 등록 성공", content = @Content(schema = @Schema(implementation = MyProfileCreateResponse.class)))
    @PostMapping
    public ResponseEntity<MyProfileCreateResponse> create(@AuthenticationPrincipal User user, @Valid @RequestBody MyProfileCreateRequest myProfileCreateRequest) {
        log.info("내 프로필 등록 userId = {}", user.getId());
        Profile profile = profileService.create(myProfileCreateRequest, user.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(MyProfileCreateResponse.from(profile));
    }

    @Operation(summary = "프로필 상세보기")
    @ApiResponse(responseCode = "200", description = "프로필 상세보기 조회 성공", content = @Content(schema = @Schema(implementation = ProfileResponse.class)))
    @GetMapping("/{userId}")
    public ResponseEntity<ProfileResponse> get(@AuthenticationPrincipal User user, @PathVariable Long userId) {
        log.info("프로필 상세보기 userId = {}", userId);
        if (user != null && user.getId().equals(userId)) {
            return ResponseEntity.ok(ProfileResponse.from(profileService.get(user.getId()), true));
        }
        return ResponseEntity.ok(ProfileResponse.from(profileService.get(userId), false));
    }

    @Operation(summary = "내 프로필 수정하기")
    @ApiResponse(responseCode = "200", description = "내 프로필 수정하기 성공", content = @Content(schema = @Schema(implementation = MyProfileUpdateResponse.class)))
    @PutMapping
    public ResponseEntity<MyProfileUpdateResponse> modify(@AuthenticationPrincipal User user, @RequestBody ProfileUpdateRequest profileUpdateRequest) {
        log.info("내 프로필 수정하기 user = {},  profileUpdateRequest = {}", profileUpdateRequest, user);
        Profile profile = profileService.modify(profileUpdateRequest, user.getId());
        return ResponseEntity.ok(MyProfileUpdateResponse.from(profile));
    }
}
