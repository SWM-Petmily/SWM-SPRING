package com.ddungja.petmily.mock;

import com.ddungja.petmily.user.service.CertificationService;
import com.ddungja.petmily.user.service.UserService;
import com.ddungja.petmily.user.service.port.CertificationAttemptRepository;
import com.ddungja.petmily.user.service.port.CertificationRepository;
import com.ddungja.petmily.user.service.port.ProfileImageRepository;
import com.ddungja.petmily.user.service.port.UserRepository;
import lombok.Builder;

public class TestContainer {
    public final UserRepository userRepository;
    public final ProfileImageRepository profileImageRepository;
    public final CertificationRepository certificationRepository;
    public final CertificationAttemptRepository certificationAttemptRepository;
    public final UserService userService;
    public final CertificationService certificationService;

    @Builder
    public TestContainer() {
        certificationRepository = new FakeCertificationRepository();
        userRepository = new FakeUserRepository();
        profileImageRepository = new FakeProfileImageRepository();
        certificationAttemptRepository = new FakeCertificationAttemptRepository();
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
    }
}
