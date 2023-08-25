package com.ddungja.petmily.user.domain.certification;

import com.ddungja.petmily.common.exception.CustomException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CertificationAttemptTest {

    @DisplayName("유저가 휴대전화 인증번호를 요청하면 인증요청횟수를 1씩 올린다. ")
    @Test
    void attempt() {
        CertificationAttempt certificationAttempt = CertificationAttempt
                .builder()
                .lastAttemptDate(LocalDate.of(2021, 1, 1))
                .attemptCount(0)
                .build();

        certificationAttempt.attempt();

        assertThat(certificationAttempt.getAttemptCount()).isEqualTo(1);
    }


    @DisplayName("유저가 휴대전화 인증번호를 하루에 5회가 넘어가면 에러를 던진다..")
    @Test
    void attemptFail() {
        CertificationAttempt certificationAttempt = CertificationAttempt
                .builder()
                .lastAttemptDate(LocalDate.of(2021, 1, 1))
                .attemptCount(5)
                .build();

        assertThatThrownBy(certificationAttempt::attempt).isInstanceOf(CustomException.class);
    }
}