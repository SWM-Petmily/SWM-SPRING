package com.ddungja.petmily.post.controller;


import com.ddungja.petmily.apply.service.ApplyService;
import com.ddungja.petmily.like.service.LikeService;
import com.ddungja.petmily.post.controller.response.*;
import com.ddungja.petmily.post.domain.Image;
import com.ddungja.petmily.post.domain.Post;
import com.ddungja.petmily.post.domain.request.PostCreateRequest;
import com.ddungja.petmily.post.domain.request.PostFilterRequest;
import com.ddungja.petmily.post.domain.type.PostStatusType;
import com.ddungja.petmily.post.service.ImageService;
import com.ddungja.petmily.post.service.PostCommandService;
import com.ddungja.petmily.post.service.PostReadService;
import com.ddungja.petmily.user.domain.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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

import static com.ddungja.petmily.post.domain.type.ImageType.POST;

@Tag(name = "Post", description = "게시글 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
@Slf4j
public class PostController {

    private final PostService postService;
    private final LikeService likeService;
    private final ApplyService applyService;
    private final ImageService imageService;

    private final RegistrationService registrationService;

    @Operation(summary = "게시글 등록")
    @ApiResponse(responseCode = "201", description = "게시글 등록 성공", content = @Content(schema = @Schema(implementation = PostCreateResponse.class)))
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostCreateResponse> create(@AuthenticationPrincipal User user,
                                    @Valid @RequestPart(value = "postRequest") PostCreateRequest postRequest,
                                    @RequestPart(value = "postImage", required = false) List<MultipartFile> postImages) throws IOException {
        log.info("게시글 등록  userId = {} postImage = {} postRequest = {}", user.getId(), postImages, postRequest);
        Post post = postCommandService.create(postRequest, user.getId(), postImages);
        return ResponseEntity.status(HttpStatus.CREATED).body(PostCreateResponse.from(post));
    }


    @Operation(summary = "게시글 상세보기")
    @ApiResponse(responseCode = "200", description = "게시글 상세보기 조회 성공", content = @Content(schema = @Schema(implementation = PostGetResponse.class)))
    @GetMapping("/{postId}")
    public ResponseEntity<PostGetResponse> getSubCategory(@AuthenticationPrincipal User user, @PathVariable Long postId) {
        log.info("게시글 상세보기 postId = {}", postId);
        Post post = postService.get(postId);
        List<Image> images = imageService.getImages(postId, POST);
        int likeCount = likeService.getLikeCountByPostId(postId);
        if(user == null) {
            return ResponseEntity.ok(PostGetResponse.from(post, images, likeCount));
        }
        Boolean isLike = likeService.isLike(user.getId(), postId);
        Boolean isApply = applyService.isApply(user.getId(), postId);
        return ResponseEntity.ok(PostGetResponse.from(user, post, images, isApply, isLike, likeCount));
    }

    @Operation(summary = "내가 작성한 게시글 가져오기")
    @ApiResponse(responseCode = "200", description = "내가 작성한 게시글 조회 성공", content = @Content(schema = @Schema(implementation = MyPostListResponse.class)))
    @GetMapping("/user")
    public ResponseEntity<Page<MyPostListResponse>> getMyPost(@AuthenticationPrincipal User user, PostStatusType status, Pageable pageable) {
        log.info("내가 작성한 게시글 가져오기 userId = {}", user.getId());
        return ResponseEntity.ok(postService.getMyPost(user.getId(), status, pageable).map(MyPostListResponse::from));
    }

    @Operation(summary = "메인 게시글 가져오기")
    @ApiResponse(responseCode = "200", description = "메인 게시글 가져오기 조회 성공", content = @Content(schema = @Schema(implementation = MainPostsResponse.class)))
    @GetMapping("/main")
    public ResponseEntity<MainPostsResponse> getMainPosts(@AuthenticationPrincipal User user, PostFilterRequest postFilterRequest, Pageable pageable) {
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

    @Operation(summary = "게시글 삭제")
    @ApiResponse(responseCode = "204", description = "게시글 삭제 성공")
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> delete(@AuthenticationPrincipal User user, @PathVariable Long postId) {
        log.info("게시글 삭제 postId = {}", postId);
        postService.delete(user.getId(), postId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "게시글 분양 완료")
    @ApiResponse(responseCode = "201", description = "게시글 분양 완료 성공", content = @Content(schema = @Schema(implementation = PostCompleteResponse.class)))
    @PutMapping("/complete/{postId}")
    public ResponseEntity<PostCompleteResponse> complete(@AuthenticationPrincipal User user, @PathVariable Long postId) {
        log.info("게시글 분양 완료 postId = {}", postId);
        postService.complete(user.getId(), postId);
        return ResponseEntity.status(HttpStatus.CREATED).body(PostCompleteResponse.from(postId));
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

