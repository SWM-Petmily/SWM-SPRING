package com.ddungja.petmily.user.service;

import com.ddungja.petmily.common.domain.exception.CustomException;
import com.ddungja.petmily.user.domain.Certification;
import com.ddungja.petmily.user.domain.User;
import com.ddungja.petmily.user.domain.request.CertificationPhoneVerifyRequest;
import com.ddungja.petmily.user.repository.CertificationRepository;
import com.ddungja.petmily.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Objects;

import static com.ddungja.petmily.common.domain.exception.ExceptionCode.*;

@Service
@Slf4j
public class CoolSmsService {
    final DefaultMessageService messageService;
    private final UserRepository userRepository;
    private final CertificationRepository certificationRepository;

    public CoolSmsService(Environment environment, UserRepository userRepository, CertificationRepository certificationRepository) {
        this.messageService = new DefaultMessageService(Objects.requireNonNull(environment.getProperty("coolsms.apiKey")), Objects.requireNonNull(environment.getProperty("coolsms.apiSecretKey")), "https://api.coolsms.co.kr");
        this.userRepository = userRepository;
        this.certificationRepository = certificationRepository;
    }

    @Transactional
    public Certification sendCertificationNumber(Long userId, String phoneNumber) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        certificationRepository.findByPhoneNumber(phoneNumber).ifPresent(
                certification -> {
                    if (certification.isCertification()) {
                        throw new CustomException(CERTIFICATION_PHONE_ALREADY_EXISTS);
                    }
                }
        );
        String certificationNumber = createCertificationNumber();
        SingleMessageSentResponse coolsmsResponse = sendCertificationNumber(phoneNumber, certificationNumber);
        log.info("인증번호 userId = {}, phoneNumber = {}, certificationNumber = {}", userId, phoneNumber, certificationNumber);
        log.info("coolsms 요청 response = {}", coolsmsResponse);
        return certificationRepository.save(Certification.builder()
                .phone(phoneNumber)
                .user(user)
                .expiredAt(LocalDateTime.now().plusMinutes(3))
                .isCertification(false)
                .certificationNumber(certificationNumber)
                .build());
    }

    @Transactional
    public void certificationVerify(Long userId, CertificationPhoneVerifyRequest certificationPhoneVerifyRequest) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        Certification certification = certificationRepository.findById(certificationPhoneVerifyRequest.getCertificationId()).orElseThrow(() -> new CustomException(CERTIFICATION_NOT_FOUND));
        certification.validation(certificationPhoneVerifyRequest, user.getId());
        certification.certification();
    }

    private SingleMessageSentResponse sendCertificationNumber(String phoneNumber, String certificationNumber) {
        Message message = new Message();
        message.setFrom("01063169037");
        message.setTo(phoneNumber);
        message.setText("[펫밀리] 본인확인 인증번호[" + certificationNumber + "]입니다. ");
        return messageService.sendOne(new SingleMessageSendingRequest(message));
    }

    private static String createCertificationNumber() {
        StringBuilder certificationNumber = new StringBuilder();
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < 6; i++) {
            certificationNumber.append(random.nextInt(10)); //10미만 난수 생성
        }
        return certificationNumber.toString();
    }

}
