package com.ddungja.app.common.domain.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum ExceptionCode {
    UN_AUTHENTICATION(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED, "로그인이 필요합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN, "권한이 없습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, "찾을 수 없습니다."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR, "서버에 오류가 발생했습니다."),


    //profile
    PROFILE_IMAGE_NOT_FOUND(1001, HttpStatus.NOT_FOUND, "프로필 이미지를 찾을 수 없습니다."),


    //user
    USER_NOT_FOUND(2001, HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다."),


    //profile
    PROFILE_NOT_FOUND(3001, HttpStatus.NOT_FOUND, "프로필을 찾을 수 없습니다."),
    PROFILE_ALREADY_EXISTS(3002, HttpStatus.BAD_REQUEST, "프로필이 이미 존재합니다."),


    //experience
    EXPERIENCE_NOT_FOUND(4001, HttpStatus.NOT_FOUND, "반려동물 경험을 찾을 수 없습니다." ),


    //category
    MAIN_CATEGORY_NOT_FOUND(4001, HttpStatus.NOT_FOUND, "메인 카테고리를 찾을 수 없습니다." ),
    SUB_CATEGORY_NOT_FOUND(4002, HttpStatus.NOT_FOUND, "서브 카테고리를 찾을 수 없습니다." );

    private final int code;
    private final HttpStatus status;
    private final String message;

    ExceptionCode(int code, HttpStatus status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }
}
