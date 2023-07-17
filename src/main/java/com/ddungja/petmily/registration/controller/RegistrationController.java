package com.ddungja.petmily.registration.controller;

import com.ddungja.petmily.registration.controller.response.MyRegistrationResponse;
import com.ddungja.petmily.registration.controller.response.RegisterCreateResponse;
import com.ddungja.petmily.registration.controller.response.SelectRegistrationResponse;
import com.ddungja.petmily.registration.domain.Registration;
import com.ddungja.petmily.registration.domain.request.RegistrationCreateRequest;
import com.ddungja.petmily.registration.service.RegistrationService;
import com.ddungja.petmily.user.domain.User;
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

    @PostMapping
    public ResponseEntity<?> create(@AuthenticationPrincipal User user, @Valid @RequestBody RegistrationCreateRequest registrationCreateRequest) {
        log.info("반려동물 등록 요청 userId = {}", user.getId());
        return ResponseEntity.ok(RegisterCreateResponse.from(registrationService.register(user.getId(), registrationCreateRequest)));
    }

    @GetMapping("/myRegister")
    public ResponseEntity<?> getMyRegister(@AuthenticationPrincipal User user) {
        log.info("반려동물 등록 조회 요청 userId = {}", user.getId());
        List<Registration> registrations= registrationService.getMyRegister(user.getId());
        return ResponseEntity.ok(registrations.stream().map(MyRegistrationResponse::from));
    }

    @DeleteMapping("/{registrationId}")
    public ResponseEntity<?> delete(@AuthenticationPrincipal User user, @PathVariable Long registrationId) {
        log.info("반려동물 등록 삭제 요청 userId = {}, registrationId = {}", user.getId(), registrationId);
        registrationService.delete(user.getId(), registrationId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("select/{registrationId}")
    public ResponseEntity<?> selectRegister(@AuthenticationPrincipal User user, @PathVariable Long registrationId) {
        log.info("반려동물 등록 선택 요청 userId = {}, registrationId = {}", user.getId(), registrationId);
        Registration registration = registrationService.select(user.getId(), registrationId);
        log.info("Registration = {}", registration);
        return ResponseEntity.ok(SelectRegistrationResponse.from(registration));
    }
}
