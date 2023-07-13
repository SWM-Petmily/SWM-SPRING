package com.ddungja.petmily.registration.controller;

import com.ddungja.petmily.common.domain.exception.CustomException;
import com.ddungja.petmily.registration.domain.request.RegistrationCreateRequest;
import com.ddungja.petmily.registration.service.RegistrationService;
import com.ddungja.petmily.user.domain.User;
import com.ddungja.petmily.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static com.ddungja.petmily.common.domain.exception.ExceptionCode.USER_NOT_FOUND;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/register")
@Slf4j
public class RegistrationController {

    private final UserService userService;

    private final RegistrationService registrationService;


    @PostMapping
    public ResponseEntity<?> create(@AuthenticationPrincipal User user, @Valid @RequestBody RegistrationCreateRequest registrationCreateRequest) {
        if(user== null) throw new CustomException(USER_NOT_FOUND);

        user = userService.get(user.getId());
        log.info("반려동물 등록 요청 userId = {}", user.getId());
        registrationService.register(user, registrationCreateRequest);
        return ResponseEntity.ok(null);

    }
}
