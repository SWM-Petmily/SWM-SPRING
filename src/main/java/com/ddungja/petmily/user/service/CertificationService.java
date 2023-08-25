package com.ddungja.petmily.user.service;

import com.ddungja.petmily.common.exception.CustomException;
import com.ddungja.petmily.common.service.ExpireTimeHolder;
import com.ddungja.petmily.user.domain.certification.Certification;
import com.ddungja.petmily.user.domain.request.CertificationPhoneNumberRequest;
import com.ddungja.petmily.user.domain.request.CertificationVerifyRequest;
import com.ddungja.petmily.user.domain.user.User;
import com.ddungja.petmily.user.service.port.CertificationAttemptRepository;
import com.ddungja.petmily.user.service.port.CertificationRepository;
import com.ddungja.petmily.user.service.port.UserRepository;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Objects;

import static com.ddungja.petmily.common.exception.ExceptionCode.*;

@Service
@Slf4j
public class CertificationService {
    final DefaultMessageService messageService;
    private final UserRepository userRepository;
    private final CertificationRepository certificationRepository;
    private final CertificationAttemptRepository certificationAttemptRepository;
    private final CertificationAttemptService certificationAttemptService;


    private final ExpireTimeHolder expireTimeHolder;

    @Builder
    public CertificationService(Environment environment, UserRepository userRepository, CertificationRepository certificationRepository, CertificationAttemptRepository certificationAttemptRepository, CertificationAttemptService certificationAttemptService, ExpireTimeHolder expireTimeHolder) {
        this.messageService = new DefaultMessageService(Objects.requireNonNull(environment.getProperty("coolsms.apiKey")), Objects.requireNonNull(environment.getProperty("coolsms.apiSecretKey")), "https://api.coolsms.co.kr");
        this.userRepository = userRepository;
        this.certificationRepository = certificationRepository;
        this.certificationAttemptRepository = certificationAttemptRepository;
        this.certificationAttemptService = certificationAttemptService;
        this.expireTimeHolder = expireTimeHolder;
    }

    @Transactional
    public void sendCertificationNumber(Long userId, String phoneNumber) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        if (user.isCertification()) {
            throw new CustomException(USER_ALREADY_CERTIFICATION);
        }
        if (userRepository.findByPhone(phoneNumber).isPresent()) {
            throw new CustomException(CERTIFICATION_PHONE_ALREADY_EXISTS);
        }

        String certificationNumber = createCertificationNumber();
        log.info("인증번호 userId = {}, phoneNumber = {}, certificationNumber = {}", userId, phoneNumber, certificationNumber);
        SingleMessageSentResponse coolsmsResponse = sendCertificationNumber(phoneNumber, certificationNumber);
        log.info("coolsms 요청 response = {}", coolsmsResponse);

        certificationAttemptService.attempt(user);
        certificationRepository.save(Certification.builder()
                .phoneNumber(phoneNumber)
                .user(user)
                .expiredAt(expireTimeHolder.millis())
                .isCertification(false)
                .certificationNumber(certificationNumber)
                .build());
    }

    @Transactional
    public void sendCertificationNumberTest(Long userId, CertificationPhoneNumberRequest certificationPhoneNumberRequest) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        if (user.isCertification()) {
            throw new CustomException(USER_ALREADY_CERTIFICATION);
        }
        if (userRepository.findByPhone(certificationPhoneNumberRequest.getPhoneNumber()).isPresent()) {
            throw new CustomException(CERTIFICATION_PHONE_ALREADY_EXISTS);
        }
        String certificationNumber = "123456";
        log.info("인증번호 userId = {}, phoneNumber = {}, certificationNumber = {}", userId, certificationPhoneNumberRequest.getPhoneNumber(), certificationNumber);
        certificationAttemptService.attempt(user);
        certificationRepository.save(Certification.builder()
                .phoneNumber(certificationPhoneNumberRequest.getPhoneNumber())
                .user(user)
                .expiredAt(expireTimeHolder.millis())
                .isCertification(false)
                .certificationNumber(certificationNumber)
                .build());
    }


    @Transactional
    public void certificationVerify(Long userId, CertificationVerifyRequest certificationPhoneVerifyRequest) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        if (user.isCertification()) {
            throw new CustomException(USER_ALREADY_CERTIFICATION);
        }
        Certification certification = certificationRepository.findFirstByUserIdOrderByIdDesc(user.getId()).orElseThrow(() -> new CustomException(CERTIFICATION_NOT_FOUND));
        certification.verify(certificationPhoneVerifyRequest);
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
