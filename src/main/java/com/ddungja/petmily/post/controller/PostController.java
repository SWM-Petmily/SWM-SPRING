package com.ddungja.petmily.post.controller;


import com.ddungja.petmily.post.controller.response.*;
import com.ddungja.petmily.post.domain.Post;
import com.ddungja.petmily.post.domain.request.PostCreateRequest;
import com.ddungja.petmily.post.domain.request.PostFilterRequest;
import com.ddungja.petmily.post.domain.type.PostStatusType;
import com.ddungja.petmily.post.service.PostService;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
@Slf4j
public class PostController {

    private final PostService postService;
    private final RegistrationService registrationService;

    @Operation(summary = "게시글 등록")
    @ApiResponse(responseCode = "201", description = "게시글 등록 성공", content = @Content(schema = @Schema(implementation = PostCreateResponse.class)))
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@AuthenticationPrincipal User user,
                                    @Valid @RequestPart(value = "postRequest") PostCreateRequest postRequest,
                                    @RequestPart(value = "postImage", required = false) List<MultipartFile> postImages) throws IOException {
        log.info("게시글 등록  userId = {} postImage = {}", user.getId(), postImages);
        Post post = postService.create(postRequest, user.getId(), postImages);
        return ResponseEntity.status(HttpStatus.CREATED).body(PostCreateResponse.from(post));
    }

    @Operation(summary = "분양 게시글 작성 시, 추가정보 입력 페이지 (인증 정보 출력)")
    @GetMapping("/certify/{postId}")
    public ResponseEntity<?> getCertify(@AuthenticationPrincipal User user, @PathVariable Long postId) {
        log.info("분양 게시글 작성 시, 추가정보 입력 페이지 (인증 정보 출력) userId = {}, postId = {}", postId);
        Post post = postService.get(postId);
        return ResponseEntity.ok(PostCertifiedResponse.from(post));
    }

    @Operation(summary = "분양게시글 작성 시, 반려동물 선택")
    @ApiResponse(responseCode = "200", description = "분양 게시글 작성 시, 반려동물 선택 성공", content = @Content(schema = @Schema(implementation = SelectRegistrationResponse.class)))
    @PostMapping("/select/{registrationId}")
    public ResponseEntity<?> selectRegister(@AuthenticationPrincipal User user, @PathVariable Long registrationId) {
        log.info("분양 게시글 작성 시, 반려동물 선택 userId = {}, registrationId = {}", user.getId(), registrationId);
        Registration registration = registrationService.select(user.getId(), registrationId);
        log.info("Registration = {}", registration);
        return ResponseEntity.ok(SelectRegistrationResponse.from(registration));
    }

    @Operation(summary = "반려동물 등록하기")
    @ApiResponse(responseCode = "201", description = "반려동물 등록하기 성공", content = @Content(schema = @Schema(implementation = PostCertifiedResponse.class)))
    @PostMapping("/certifyRegistration/{postId}")
    public ResponseEntity<?> certifyRegistration(@AuthenticationPrincipal User user, @Valid @RequestBody RegistrationCreateRequest registrationCreateRequest, @PathVariable Long postId) {
        log.info("반려동물 등록하기 userId = {}, postId = {}", user.getId(), postId);
        Registration registration = registrationService.register(user.getId(), registrationCreateRequest);
        Post post = postService.certifyRegistration(user.getId(), postId);
        return ResponseEntity.status(HttpStatus.CREATED).body(PostCertifiedResponse.from(post));
    }


    @Operation(summary = "예방접종 인증")
    @ApiResponse(responseCode = "201", description = "예방접종 인증 성공", content = @Content(schema = @Schema(implementation = PostCertifiedResponse.class)))
    @PostMapping(path= "/certifyVaccination/{postId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> certifyVaccination(@AuthenticationPrincipal User user, @PathVariable Long postId, @RequestPart(value = "vaccinationImages") List<MultipartFile> vaccinationImages) throws IOException {
        log.info("예방접종 인증 postId = {} vaccinationImages = {}", postId, vaccinationImages);
        Post post = postService.certifyVaccination(postId, user.getId(), vaccinationImages);
        return ResponseEntity.status(HttpStatus.CREATED).body(PostCertifiedResponse.from(post));
    }


    @Operation(summary = "건강검진 인증")
    @ApiResponse(responseCode = "201", description = "건강검진 인증 성공", content = @Content(schema = @Schema(implementation = PostCertifiedResponse.class)))
    @PostMapping(path= "/certifyMedicalCheck/{postId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> certifyMedicalCheck(@AuthenticationPrincipal User user, @PathVariable Long postId, @RequestPart(value = "medicalCheckImages") List<MultipartFile> medicalCheckImages) throws IOException {
        log.info("건강검진 인증 postId = {} medicalCheckImages = {}", postId, medicalCheckImages);
        Post post = postService.certifyMedicalCheck(postId, user.getId(), medicalCheckImages);
        return ResponseEntity.status(HttpStatus.CREATED).body(PostCertifiedResponse.from(post));
    }


    @Operation(summary = "게시글 상세보기")
    @ApiResponse(responseCode = "200", description = "게시글 상세보기 조회 성공", content = @Content(schema = @Schema(implementation = PostDetailResponse.class)))
    @GetMapping("/{postId}")
    public ResponseEntity<?> getSubCategory(@AuthenticationPrincipal User user, @PathVariable Long postId) {
        log.info("게시글 상세보기 postId = {}", postId);
        return ResponseEntity.ok( postService.detail(postId, user));
    }

    @Operation(summary = "내가 작성한 게시글 가져오기")
    @ApiResponse(responseCode = "200", description = "내가 작성한 게시글 조회 성공", content = @Content(schema = @Schema(implementation = MyPostListResponse.class)))
    @GetMapping("/user")
    public ResponseEntity<?> getMyPost(@AuthenticationPrincipal User user, PostStatusType status, Pageable pageable) {
        log.info("내가 작성한 게시글 가져오기 userId = {}", user.getId());
        return ResponseEntity.ok(postService.getMyPost(user.getId(), status, pageable).map(MyPostListResponse::from));
    }

    @Operation(summary = "메인 게시글 가져오기")
    @ApiResponse(responseCode = "200", description = "메인 게시글 가져오기 조회 성공", content = @Content(schema = @Schema(implementation = MainPostResponse.class)))
    @GetMapping("/main")
    public ResponseEntity<?> getMainPosts(@AuthenticationPrincipal User user, PostFilterRequest postFilterRequest, Pageable pageable) {
        List<String> filter = getFilter(postFilterRequest);
        if (user == null) {
            log.info("메인 게시글 가져오기 - 비로그인");
            Page<MainPostResponse> mainPostResponses = postService.getMainPosts(postFilterRequest, pageable).map(MainPostResponse::from);
            MainPostsResponse mainPostsResponses = MainPostsResponse.from(filter,mainPostResponses.getContent(), mainPostResponses.getTotalPages(), mainPostResponses.getTotalElements());
            return ResponseEntity.ok(mainPostsResponses);

        }
        log.info("메인 게시글 가져오기 - 로그인 userId = {}", user.getId());
        Page<MainPostResponse> mainPostResponses = postService.getMainPosts(user.getId(), postFilterRequest, pageable).map(post -> MainPostResponse.from(user.getId(), post));
        MainPostsResponse mainPostsResponses = MainPostsResponse.from(filter,mainPostResponses.getContent(), mainPostResponses.getTotalPages(), mainPostResponses.getTotalElements());
        return ResponseEntity.ok(mainPostsResponses);
    }

    private static List<String> getFilter(PostFilterRequest postFilterRequest) {
        List<String> filter = new ArrayList<>();
        if(StringUtils.hasText(postFilterRequest.getRegion()))  filter.add(postFilterRequest.getRegion());
        if(StringUtils.hasText(postFilterRequest.getMainCategory()))  filter.add(postFilterRequest.getMainCategory());
        if(StringUtils.hasText(postFilterRequest.getSubCategory())) filter.add(postFilterRequest.getSubCategory());
        if(postFilterRequest.getGenderType() != null)  filter.add(postFilterRequest.getGenderType().toString());
        if(postFilterRequest.getNeuteredType() != null)  filter.add(postFilterRequest.getNeuteredType().toString());
        if(postFilterRequest.getMoneyFrom() != null && postFilterRequest.getMoneyTo() != null){
            int moneyFrom = postFilterRequest.getMoneyFrom();
            int moneyTo = postFilterRequest.getMoneyTo();
            String money = moneyFrom + "원 - " + moneyTo + "원";
            filter.add(money);
        }
        if(postFilterRequest.getAgeFrom() != null && postFilterRequest.getAgeTo() != null){
            int ageFrom = postFilterRequest.getAgeFrom();
            int ageTo = postFilterRequest.getAgeTo();
            String age = ageFrom + "개월 - " + ageTo + "개월";
            filter.add(age);
        }
        return filter;
    }
}

