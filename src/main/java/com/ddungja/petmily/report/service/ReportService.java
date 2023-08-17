package com.ddungja.petmily.report.service;

import com.ddungja.petmily.common.exception.CustomException;
import com.ddungja.petmily.post.domain.Post;
import com.ddungja.petmily.post.service.port.PostRepository;
import com.ddungja.petmily.report.domain.Report;
import com.ddungja.petmily.report.repository.ReportRepository;
import com.ddungja.petmily.user.domain.user.User;
import com.ddungja.petmily.user.service.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ddungja.petmily.common.exception.ExceptionCode.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReportService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ReportRepository reportRepository;

    @Transactional
    public void reportPost(Long userId, Long postId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        Post post = postRepository.findById(postId).orElseThrow(() -> new CustomException(POST_NOT_FOUND));
        if(isReport(user.getId(), post.getId())){
            throw new CustomException(ALREADY_REPORT);
        }
        post.report();
        reportRepository.save(Report.from(user, post));
    }

    private boolean isReport(Long userId, Long postId){
        return reportRepository.existsByUserIdAndPostId(userId, postId);
    }
}
