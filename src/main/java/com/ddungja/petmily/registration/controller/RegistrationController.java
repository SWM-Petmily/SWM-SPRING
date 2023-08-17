package com.ddungja.petmily.registration.controller;

import com.ddungja.petmily.registration.controller.response.MyRegistrationResponse;
import com.ddungja.petmily.registration.controller.response.MyRegistrationsResponse;
import com.ddungja.petmily.registration.controller.response.RegisterCreateResponse;
import com.ddungja.petmily.registration.domain.Registration;
import com.ddungja.petmily.registration.domain.request.RegistrationCreateRequest;
import com.ddungja.petmily.registration.service.RegistrationService;
import com.ddungja.petmily.user.domain.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Registration", description = "반려동물 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users/register")
@Slf4j
public class RegistrationController {
    private final RegistrationService registrationService;

    @Operation(summary = "반려동물 등록하기")
    @ApiResponse(responseCode = "201", description = "반려동물 등록하기 성공", content = @Content(schema = @Schema(implementation = RegisterCreateResponse.class)))
    @PostMapping
    public ResponseEntity<RegisterCreateResponse> create(@AuthenticationPrincipal User user, @Valid @RequestBody RegistrationCreateRequest registrationCreateRequest) {
        log.info("반려동물 등록하기 userId = {}", user.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(RegisterCreateResponse.from(registrationService.register(user.getId(), registrationCreateRequest)));
    }
    @Operation(summary = "내가 등록한 반려동물 보기")
    @ApiResponse(responseCode = "200", description = "내가 등록한 반려동물 조회 성공", content = @Content(schema = @Schema(implementation = MyRegistrationsResponse.class)))
    @GetMapping("/myRegister")
    public ResponseEntity<MyRegistrationsResponse> getMyRegister(@AuthenticationPrincipal User user) {
        log.info("내가 등록한 반려동물 보기 userId = {}", user.getId());
        List<Registration> registrations= registrationService.getMyRegister(user.getId());
        return ResponseEntity.ok(MyRegistrationsResponse.from(registrations.stream().map(MyRegistrationResponse::from)));
    }
    @Operation(summary = "반려동물 삭제하기")
    @ApiResponse(responseCode = "204", description = "반려동물 삭제하기 성공")
    @DeleteMapping("/{registrationId}")
    public ResponseEntity<Void> delete(@AuthenticationPrincipal User user, @PathVariable Long registrationId) {
        log.info("반려동물 삭제하기 userId = {}, registrationId = {}", user.getId(), registrationId);
        registrationService.delete(user.getId(), registrationId);
        return ResponseEntity.noContent().build();
    }

}
