package com.ddungja.petmily.common.domain.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum ExceptionCode {
    //권한 관련
    UN_AUTHENTICATION(0001, HttpStatus.UNAUTHORIZED, "로그인이 필요합니다."),
    FORBIDDEN(0002, HttpStatus.FORBIDDEN, "권한이 없습니다."),
    REFRESH_TOKEN_NOT_FOUND(0003, HttpStatus.NOT_FOUND, "리프레시 토큰을 찾을 수 없습니다." ),
    REFRESH_TOKEN_VALIDATION_FAILED(0004, HttpStatus.UNAUTHORIZED, "리프레시 토큰이 유효하지 않습니다." ),

    //user
    USER_NOT_FOUND(2001, HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다."),


    //profile
    PROFILE_NOT_FOUND(3001, HttpStatus.NOT_FOUND, "프로필을 찾을 수 없습니다."),
    PROFILE_ALREADY_EXISTS(3002, HttpStatus.BAD_REQUEST, "프로필이 이미 존재합니다."),
    PROFILE_IMAGE_NOT_FOUND(3003, HttpStatus.NOT_FOUND, "프로필 이미지를 찾을 수 없습니다."),



    //experience
    EXPERIENCE_NOT_FOUND(4001, HttpStatus.NOT_FOUND, "반려동물 경험을 찾을 수 없습니다." ),


    //category
    MAIN_CATEGORY_NOT_FOUND(4001, HttpStatus.NOT_FOUND, "메인 카테고리를 찾을 수 없습니다." ),
    SUB_CATEGORY_NOT_FOUND(4002, HttpStatus.NOT_FOUND, "서브 카테고리를 찾을 수 없습니다." ),

    //post
    POST_NOT_FOUND(5001, HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다."),


    //apply
    APPLY_NOT_FOUND(6001, HttpStatus.NOT_FOUND, "지원서를 찾을 수 없습니다." ),
    APPLY_ALREADY_EXISTS(6002, HttpStatus.BAD_REQUEST, "이미 지원서를 작성했습니다." ),

    //like
    LIKE_NOT_FOUND(6001, HttpStatus.NOT_FOUND, "좋아요를 찾을 수 없습니다."),
    LIKE_IS_EXISTS(6002, HttpStatus.BAD_REQUEST, "이미 좋아요를 눌렀습니다.");


    private final int code;
    private final HttpStatus status;
    private final String message;

    ExceptionCode(int code, HttpStatus status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }
}
