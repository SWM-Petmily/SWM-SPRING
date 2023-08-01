package com.ddungja.petmily.registration.controller;

import com.ddungja.petmily.registration.controller.response.MyRegistrationResponse;
import com.ddungja.petmily.registration.controller.response.RegisterCreateResponse;
import com.ddungja.petmily.registration.controller.response.SelectRegistrationResponse;
import com.ddungja.petmily.registration.domain.Registration;
import com.ddungja.petmily.registration.domain.request.RegistrationCreateRequest;
import com.ddungja.petmily.registration.service.RegistrationService;
import com.ddungja.petmily.user.domain.user.User;
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

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/register")
@Slf4j
public class RegistrationController {
    private final RegistrationService registrationService;

    @Operation(summary = "반려동물 등록하기")
    @ApiResponse(responseCode = "201", description = "반려동물 등록하기 성공", content = @Content(schema = @Schema(implementation = RegisterCreateResponse.class)))
    @PostMapping
    public ResponseEntity<?> create(@AuthenticationPrincipal User user, @Valid @RequestBody RegistrationCreateRequest registrationCreateRequest) {
        log.info("반려동물 등록하기 userId = {}", user.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(RegisterCreateResponse.from(registrationService.register(user.getId(), registrationCreateRequest)));
    }
    @Operation(summary = "내가 등록한 반려동물 보기")
    @ApiResponse(responseCode = "200", description = "내가 등록한 반려동물 조회 성공", content = @Content(schema = @Schema(implementation = MyRegistrationResponse.class)))
    @GetMapping("/myRegister")
    public ResponseEntity<?> getMyRegister(@AuthenticationPrincipal User user) {
        log.info("내가 등록한 반려동물 보기 userId = {}", user.getId());
        List<Registration> registrations= registrationService.getMyRegister(user.getId());
        return ResponseEntity.ok(registrations.stream().map(MyRegistrationResponse::from));
    }
    @Operation(summary = "반려동물 삭제하기")
    @ApiResponse(responseCode = "204", description = "반려동물 삭제하기 성공")
    @DeleteMapping("/{registrationId}")
    public ResponseEntity<?> delete(@AuthenticationPrincipal User user, @PathVariable Long registrationId) {
        log.info("반려동물 삭제하기 userId = {}, registrationId = {}", user.getId(), registrationId);
        registrationService.delete(user.getId(), registrationId);
        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "분양 게시글 작성 시, 반려동물 선택")
    @ApiResponse(responseCode = "200", description = "분양 게시글 작성 시, 반려동물 선택 성공", content = @Content(schema = @Schema(implementation = SelectRegistrationResponse.class)))
    @GetMapping("/select/{registrationId}")
    public ResponseEntity<?> selectRegister(@AuthenticationPrincipal User user, @PathVariable Long registrationId) {
        log.info("분양 게시글 작성 시, 반려동물 선택 userId = {}, registrationId = {}", user.getId(), registrationId);
        Registration registration = registrationService.select(user.getId(), registrationId);
        log.info("Registration = {}", registration);
        return ResponseEntity.ok(SelectRegistrationResponse.from(registration));
    }
}
