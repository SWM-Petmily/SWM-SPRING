package com.ddungja.petmily.mock;

import com.ddungja.petmily.apply.service.ApplyService;
import com.ddungja.petmily.apply.service.port.ApplyRepository;
import com.ddungja.petmily.common.jwt.JwtProvider;
import com.ddungja.petmily.like.service.LikeService;
import com.ddungja.petmily.like.service.port.LikeRepository;
import com.ddungja.petmily.mock.repository.*;
import com.ddungja.petmily.post.service.port.PostRepository;
import com.ddungja.petmily.user.controller.UserController;
import com.ddungja.petmily.user.service.CertificationService;
import com.ddungja.petmily.user.service.UserService;
import com.ddungja.petmily.user.service.port.CertificationAttemptRepository;
import com.ddungja.petmily.user.service.port.CertificationRepository;
import com.ddungja.petmily.user.service.port.ProfileImageRepository;
import com.ddungja.petmily.user.service.port.UserRepository;
import lombok.Builder;

public class TestContainer {
    public final UserRepository userRepository;
    public final PostRepository postRepository;
    public final LikeRepository likeRepository;
    public final ApplyRepository applyRepository;
    public final ProfileImageRepository profileImageRepository;
    public final CertificationRepository certificationRepository;
    public final CertificationAttemptRepository certificationAttemptRepository;
    public final UserService userService;
    public final ApplyService applyService;
    public final LikeService likeService;
    public final CertificationService certificationService;
    public final UserController userController;


    @Builder
    public TestContainer() {
        certificationRepository = new FakeCertificationRepository();
        userRepository = new FakeUserRepository();
        postRepository = new FakePostRepository();
        likeRepository = new FakeLikeRepository();
        applyRepository = new FakeApplyRepository();
        profileImageRepository = new FakeProfileImageRepository();
        certificationAttemptRepository = new FakeCertificationAttemptRepository();
        applyService = ApplyService.builder()
                .applyRepository(applyRepository)
                .postRepository(postRepository)
                .userRepository(userRepository)
                .build();
        likeService = LikeService.builder()
                .likeRepository(likeRepository)
                .postRepository(postRepository)
                .userRepository(userRepository)
                .build();
        certificationService = CertificationService.builder()
                .certificationRepository(certificationRepository)
                .environment(new FakeEnvironment())
                .certificationRepository(certificationRepository)
                .certificationAttemptRepository(certificationAttemptRepository)
                .build();
        userService = UserService.builder()
                .certificationRepository(certificationRepository)
                .profileImageRepository(profileImageRepository)
                .userRepository(userRepository)
                .build();
        userController = UserController.builder()
                .jwtProvider(new JwtProvider())
                .kakaoService(new FakeKaKaoService())
                .certificationService(certificationService)
                .userService(userService)
                .build();
    }
}
