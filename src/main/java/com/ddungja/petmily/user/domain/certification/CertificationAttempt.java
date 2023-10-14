package com.ddungja.petmily.user.domain.certification;

import com.ddungja.petmily.common.exception.CustomException;
import com.ddungja.petmily.user.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static com.ddungja.petmily.common.exception.ExceptionCode.CERTIFICATION_ATTEMPT_EXCEED;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "certification_attempts")
public class CertificationAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate lastAttemptDate;
    private int attemptCount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private User user;
    @Builder
    public CertificationAttempt(Long id, LocalDate lastAttemptDate, int attemptCount, User user) {
        this.id = id;
        this.lastAttemptDate = lastAttemptDate;
        this.attemptCount = attemptCount;
        this.user = user;
    }

    public void attempt() {
        if (isExceed()) {
            throw new CustomException(CERTIFICATION_ATTEMPT_EXCEED);
        }
        attemptCount++;
    }

    private boolean isExceed() {
        return attemptCount >= 5;
    }
}
