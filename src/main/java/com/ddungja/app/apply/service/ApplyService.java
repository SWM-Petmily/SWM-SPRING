package com.ddungja.app.apply.service;


import com.ddungja.app.post.repository.PostRepository;
import com.ddungja.app.apply.repository.ApplyExperienceRepository;
import com.ddungja.app.apply.repository.ApplyRepository;
import com.ddungja.app.users.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplyService {
    private final ApplyRepository applyRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ApplyExperienceRepository applyExperienceRepository;

//    public Apply getByPostId(Long id, Long postId) {
//        User user = userRepository.findById(id).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
//        Post post = postRepository.findById(postId).orElseThrow(() -> new CustomException(POST_NOT_FOUND));
//        List<Apply> applies = applyRepository.findBySellerId(user.getId());
//        if (apply.isExperience()) {
//            List<ApplyExperience> applyExperiences = applyExperienceRepository.findByApplyId(apply.getId());
//        }
//
//
//    }
}
