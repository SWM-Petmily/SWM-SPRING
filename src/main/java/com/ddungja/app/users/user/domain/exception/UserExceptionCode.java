package com.ddungja.app.users.user.domain.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum UserExceptionCode {
    USER_NOT_FOUND(1001, HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),
    USER_ALREADY_EXIST(1002, HttpStatus.BAD_REQUEST, "이미 존재하는 사용자입니다.");

    private final int code;
    private final HttpStatus status;
    private final String message;

    UserExceptionCode(int code, HttpStatus status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }
}
