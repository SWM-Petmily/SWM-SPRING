package com.ddungja.petmily.post.service;

import com.ddungja.petmily.common.exception.CustomException;
import com.ddungja.petmily.post.domain.Post;
import com.ddungja.petmily.post.domain.request.PostFilterRequest;
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

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostReadService {

    private final UserRepository userRepository;
    private final ApplyJpaRepository applyRepository;
    private final PostRepository postRepository;
    private final ReportRepository reportRepository;

    public Post get(Long postId) {
        return postRepository.findPostById(postId).orElseThrow(() -> new CustomException(POST_NOT_FOUND));
    }

    public Page<Post> getMyPost(Long userId, PostStatusType postStatusType, Pageable pageable) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        return postRepository.getMyPost(user.getId(), postStatusType, pageable);
    }

    public Page<Post> getMainPosts(Long userId, PostFilterRequest postFilterRequest, Pageable pageable) {
        if (!userRepository.existsById(userId)) {
            throw new CustomException(USER_NOT_FOUND);
        }
        List<Long> reportPostIds = reportRepository.findByUserId(userId).stream().map(report -> report.getPost().getId()).toList();
        return postRepository.getMainPosts(userId, postFilterRequest, reportPostIds, pageable);
    }

    public Page<Post> getMainPosts(PostFilterRequest postFilterRequest, Pageable pageable) {
        return postRepository.getMainPosts(postFilterRequest, pageable);
    }

}