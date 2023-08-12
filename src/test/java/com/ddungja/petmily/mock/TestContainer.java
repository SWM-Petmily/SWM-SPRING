package com.ddungja.petmily.mock;

import com.ddungja.petmily.user.service.CertificationService;
import com.ddungja.petmily.user.service.UserService;
import com.ddungja.petmily.user.service.port.CertificationRepository;
import com.ddungja.petmily.user.service.port.ProfileImageRepository;
import com.ddungja.petmily.user.service.port.UserRepository;
import lombok.Builder;

public class TestContainer {
    public final UserRepository userRepository;
    public final ProfileImageRepository profileImageRepository;
    public final CertificationRepository certificationRepository;
    public final UserService userService;
    public final CertificationService certificationService;

    @Builder
    public TestContainer() {
        certificationRepository = new FakeCertificationRepository();
        userRepository = new FakeUserRepository();
        profileImageRepository = new FakeProfileImageRepository();

        certificationService = CertificationService.builder()
                .certificationRepository(certificationRepository)
                .build();
        userService = UserService.builder()
                .certificationRepository(certificationRepository)
                .userRepository(userRepository)
                .build();
    }
}
