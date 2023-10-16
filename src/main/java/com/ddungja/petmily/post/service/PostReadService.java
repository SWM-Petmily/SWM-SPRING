package com.ddungja.petmily.post.service;

import com.ddungja.petmily.apply.service.ApplyService;
import com.ddungja.petmily.common.exception.CustomException;
import com.ddungja.petmily.like.service.LikeService;
import com.ddungja.petmily.post.controller.response.MainPostResponse;
import com.ddungja.petmily.post.controller.response.PostGetResponse;
import com.ddungja.petmily.post.domain.Image;
import com.ddungja.petmily.post.domain.Post;
import com.ddungja.petmily.post.domain.request.PostFilterRequest;
import com.ddungja.petmily.post.domain.response.MyPostListResponse;
import com.ddungja.petmily.post.domain.type.PostStatusType;
import com.ddungja.petmily.post.service.port.PostRepository;
import com.ddungja.petmily.report.repository.ReportRepository;
import com.ddungja.petmily.user.domain.user.User;
import com.ddungja.petmily.user.service.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.ddungja.petmily.common.exception.ExceptionCode.POST_NOT_FOUND;
import static com.ddungja.petmily.common.exception.ExceptionCode.USER_NOT_FOUND;
import static com.ddungja.petmily.post.domain.type.ImageType.POST;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostReadService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ReportRepository reportRepository;
    private final ImageService imageService;
    private final ApplyService applyService;
    private final LikeService likeService;

    @Transactional
    public Post get(Long postId) {
        return postRepository.findPostById(postId).orElseThrow(() -> new CustomException(POST_NOT_FOUND));
    }

    public PostGetResponse getDetail(User user, Long postId){
        Post post = postRepository.findPostById(postId).orElseThrow(() -> new CustomException(POST_NOT_FOUND));
        List<Image> images = imageService.getImages(postId, POST);

        //todo 나중에 주석
        String thumbnailImage = post.getThumbnailImage();
        Image image = new Image(thumbnailImage);
        images.add(0,image);
        //todo 나중에 주석

        int likeCount = likeService.getLikeCountByPostId(postId);
        if(user == null) {
            return PostGetResponse.from(post, images, likeCount);
        }
        if (!userRepository.existsById(user.getId())) throw new CustomException(USER_NOT_FOUND);
        Boolean isLike = likeService.isLike(user.getId(), postId);
        Boolean isApply = applyService.isApply(user.getId(), postId);
        return PostGetResponse.from(user, post, images, isApply, isLike, likeCount);
    }


    public Page<MyPostListResponse> getMyPost(Long userId, PostStatusType postStatusType, Pageable pageable) {
        if (!userRepository.existsById(userId)) throw new CustomException(USER_NOT_FOUND);
        return postRepository.getMyPost(userId, postStatusType, pageable).map(MyPostListResponse::from);
    }

    public Page<MainPostResponse> getMainPosts(Long userId, PostFilterRequest postFilterRequest, Pageable pageable) {
        if (!userRepository.existsById(userId)) throw new CustomException(USER_NOT_FOUND);
        List<Long> reportPostIds = reportRepository.findByUserId(userId).stream().map(report -> report.getPost().getId()).toList();
        return postRepository.getMainPosts(userId, postFilterRequest, reportPostIds, pageable).map(post -> MainPostResponse.from(userId, post));
    }

    public Page<Post> getMainPosts(PostFilterRequest postFilterRequest, Pageable pageable) {
        return postRepository.getMainPosts(postFilterRequest, pageable);
    }

}