package com.ddungja.petmily.mock;

import com.ddungja.petmily.apply.service.ApplyService;
import com.ddungja.petmily.apply.service.port.ApplyRepository;
import com.ddungja.petmily.common.jwt.JwtProvider;
import com.ddungja.petmily.common.service.ExpireTimeHolder;
import com.ddungja.petmily.like.service.LikeService;
import com.ddungja.petmily.like.service.port.LikeRepository;
import com.ddungja.petmily.mock.repository.*;
import com.ddungja.petmily.post.service.port.PostRepository;
import com.ddungja.petmily.user.controller.UserController;
import com.ddungja.petmily.user.service.CertificationAttemptService;
import com.ddungja.petmily.user.service.CertificationService;
import com.ddungja.petmily.user.service.ProfileService;
import com.ddungja.petmily.user.service.UserService;
import com.ddungja.petmily.user.service.port.*;
import lombok.Builder;

public class TestContainer {

    //etc
    public final ExpireTimeHolder expireTimeHolder;

    //repository
    public final UserRepository userRepository;
    public final PostRepository postRepository;
    public final LikeRepository likeRepository;
    public final ApplyRepository applyRepository;
    public final ProfileImageRepository profileImageRepository;
    public final ProfileRepository profileRepository;
    public final CertificationRepository certificationRepository;
    public final CertificationAttemptRepository certificationAttemptRepository;


    //service
    public final UserService userService;
    public final ApplyService applyService;
    public final LikeService likeService;
    public final CertificationService certificationService;
    public final CertificationAttemptService certificationAttemptService;

    public final ProfileService profileService;

    //controller
    public final UserController userController;



    @Builder
    public TestContainer() {
        profileRepository = new FakeProfileRepository();
        expireTimeHolder = new TestExpireTimeHolder(3);
        certificationRepository = new FakeCertificationRepository();
        userRepository = new FakeUserRepository();
        postRepository = new FakePostRepository();
        likeRepository = new FakeLikeRepository();
        applyRepository = new FakeApplyRepository();
        profileImageRepository = new FakeProfileImageRepository();
        certificationAttemptRepository = new FakeCertificationAttemptRepository();
        certificationAttemptService = CertificationAttemptService.builder()
                .certificationAttemptRepository(certificationAttemptRepository)
                .build();
        profileService = ProfileService.builder()
                .profileRepository(profileRepository)
                .userRepository(userRepository)
                .profileImageRepository(profileImageRepository)
                .build();
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
                .expireTimeHolder(expireTimeHolder)
                .certificationRepository(certificationRepository)
                .environment(new FakeEnvironment())
                .certificationRepository(certificationRepository)
                .certificationAttemptService(certificationAttemptService)
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
