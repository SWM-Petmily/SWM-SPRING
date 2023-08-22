package com.ddungja.petmily.post.controller;

import com.ddungja.petmily.post.controller.response.PostCertifiedResponse;
import com.ddungja.petmily.post.domain.Post;
import com.ddungja.petmily.post.service.PostCommandService;
import com.ddungja.petmily.post.service.PostReadService;
import com.ddungja.petmily.registration.controller.response.SelectRegistrationResponse;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.ddungja.petmily.post.domain.type.ImageType.MEDICAL_CHECK;
import static com.ddungja.petmily.post.domain.type.ImageType.VACCINATION;

@Tag(name = "Post", description = "게시글 인증 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
@Slf4j
public class PostCertificationController {

    private final PostReadService postReadService;
    private final PostCommandService postCommandService;
    private final RegistrationService registrationService;

    @Operation(summary = "분양 게시글 작성 시, 추가정보 입력 페이지 (인증 정보 출력)")
    @ApiResponse(responseCode = "200", description = "분양 게시글 작성 시, 추가정보 입력 페이지 (인증 정보 출력)", content = @Content(schema = @Schema(implementation = PostCertifiedResponse.class)))
    @GetMapping("/certify/{postId}")
    public ResponseEntity<PostCertifiedResponse> getCertify(@AuthenticationPrincipal User user, @PathVariable Long postId) {
        log.info("분양 게시글 작성 시, 추가정보 입력 페이지 (인증 정보 출력) userId = {}, postId = {}", postId);
        Post post = postReadService.get(postId);
        return ResponseEntity.ok(PostCertifiedResponse.from(post));
    }

    @Operation(summary = "분양게시글 작성 시, 반려동물 선택")
    @ApiResponse(responseCode = "200", description = "분양 게시글 작성 시, 반려동물 선택 성공", content = @Content(schema = @Schema(implementation = SelectRegistrationResponse.class)))
    @PostMapping("/select/{registrationId}")
    public ResponseEntity<SelectRegistrationResponse> selectRegister(@AuthenticationPrincipal User user, @PathVariable Long registrationId) {
        log.info("분양 게시글 작성 시, 반려동물 선택 userId = {}, registrationId = {}", user.getId(), registrationId);
        Registration registration = registrationService.select(user.getId(), registrationId);
        log.info("Registration = {}", registration);
        return ResponseEntity.ok(SelectRegistrationResponse.from(registration));
    }

    @Operation(summary = "반려동물 등록하기")
    @ApiResponse(responseCode = "201", description = "반려동물 등록하기 성공", content = @Content(schema = @Schema(implementation = PostCertifiedResponse.class)))
    @PostMapping("/certifyRegistration/{postId}")
    public ResponseEntity<PostCertifiedResponse> certifyRegistration(@AuthenticationPrincipal User user, @Valid @RequestBody RegistrationCreateRequest registrationCreateRequest, @PathVariable Long postId) {
        log.info("반려동물 등록하기 userId = {}, postId = {}", user.getId(), postId);
        registrationService.register(user.getId(), registrationCreateRequest);
        Post post = postCommandService.certifyRegistration(user.getId(), postId);
        return ResponseEntity.status(HttpStatus.CREATED).body(PostCertifiedResponse.from(post));
    }

    @Operation(summary = "예방접종 인증")
    @ApiResponse(responseCode = "201", description = "예방접종 인증 성공", content = @Content(schema = @Schema(implementation = PostCertifiedResponse.class)))
    @PostMapping(path= "/certifyVaccination/{postId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostCertifiedResponse> certifyVaccination(@AuthenticationPrincipal User user, @PathVariable Long postId, @RequestPart(value = "vaccinationImages") List<MultipartFile> vaccinationImages) throws IOException {
        log.info("예방접종 인증 postId = {} vaccinationImages = {}", postId, vaccinationImages);
        Post post = postCommandService.certifyCheck(postId, user.getId(), vaccinationImages, VACCINATION);
        return ResponseEntity.status(HttpStatus.CREATED).body(PostCertifiedResponse.from(post));
    }


    @Operation(summary = "건강검진 인증")
    @ApiResponse(responseCode = "201", description = "건강검진 인증 성공", content = @Content(schema = @Schema(implementation = PostCertifiedResponse.class)))
    @PostMapping(path= "/certifyMedicalCheck/{postId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostCertifiedResponse> certifyMedicalCheck(@AuthenticationPrincipal User user, @PathVariable Long postId, @RequestPart(value = "medicalCheckImages") List<MultipartFile> medicalCheckImages) throws IOException {
        log.info("건강검진 인증 postId = {} medicalCheckImages = {}", postId, medicalCheckImages);
        Post post = postCommandService.certifyCheck(postId, user.getId(), medicalCheckImages, MEDICAL_CHECK);
        return ResponseEntity.status(HttpStatus.CREATED).body(PostCertifiedResponse.from(post));
    }
}
