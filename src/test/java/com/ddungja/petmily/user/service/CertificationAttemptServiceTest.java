package com.ddungja.petmily.user.service;

import com.ddungja.petmily.common.exception.CustomException;
import com.ddungja.petmily.mock.TestContainer;
import com.ddungja.petmily.user.domain.certification.CertificationAttempt;
import com.ddungja.petmily.user.domain.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CertificationAttemptServiceTest {


    @DisplayName("휴대전화 인증번호 요청을 하면 요청횟수를 1올린다.")
    @Test
    void attempt() {
        //given
        TestContainer testContainer = TestContainer.builder().build();

        User user = testContainer.userRepository.save(User.builder()
                .isCertification(false)
                .nickname("test")
                .build());

        //when
        testContainer.certificationAttemptService.attempt(user);


        //then
        CertificationAttempt certificationAttempt = testContainer.certificationAttemptRepository.findByUserIdAndLastAttemptDate(user.getId(), LocalDate.now()).get();
        assertThat(certificationAttempt.getAttemptCount()).isEqualTo(1L);
    }


    @DisplayName("휴대전화 인증번호 요청이 5번이 넘어가면 에러를 던진다..")
    @Test
    void attemptFail() {
        //given
        TestContainer testContainer = TestContainer.builder().build();

        User user = testContainer.userRepository.save(User.builder()
                .isCertification(false)
                .nickname("test")
                .build());

        //when
        testContainer.certificationAttemptService.attempt(user);
        testContainer.certificationAttemptService.attempt(user);
        testContainer.certificationAttemptService.attempt(user);
        testContainer.certificationAttemptService.attempt(user);
        testContainer.certificationAttemptService.attempt(user);
        assertThatThrownBy(() -> testContainer.certificationAttemptService.attempt(user))
                .isInstanceOf(CustomException.class);

    }
}