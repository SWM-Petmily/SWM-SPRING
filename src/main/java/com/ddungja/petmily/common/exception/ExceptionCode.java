package com.ddungja.petmily.common.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
@ToString
public enum ExceptionCode {
    //권한 관련
    UN_AUTHENTICATION(1001, HttpStatus.UNAUTHORIZED, "로그인이 필요합니다.", "제목", "메시지", "exclamationmark.circle", "#fa0515"),
    FORBIDDEN(1002, HttpStatus.FORBIDDEN, "권한이 없습니다.", "제목", "메시지", "exclamationmark.circle", "#fa0515"),
    REFRESH_TOKEN_NOT_FOUND(1003, HttpStatus.NOT_FOUND, "리프레시 토큰을 찾을 수 없습니다.", "제목", "메시지", "exclamationmark.circle", "#fa0515"),
    REFRESH_TOKEN_VALIDATION_FAILED(1004, HttpStatus.UNAUTHORIZED, "리프레시 토큰이 유효하지 않습니다.", "제목", "메시지", "exclamationmark.circle", "#fa0515"),
    INVALID_APPLE_CLAIMS(1005, HttpStatus.BAD_REQUEST, "애플 claims가 유효하지 않습니다.", "제목", "메시지", "exclamationmark.circle", "#fa0515"),
    INVALID_APPLE_IDENTITY_TOKEN(1006, HttpStatus.BAD_REQUEST, "Apple OAuth Identity Token 형식이 올바르지 않습니다.", "제목", "메시지", "exclamationmark.circle", "#fa0515"),
    INVALID_APPLE_IDENTITY_TOKEN_EXPIRED(1007, HttpStatus.BAD_REQUEST, "Apple OAuth 로그인 중 Identity Token 유효기간이 만료됐습니다.", "제목", "메시지", "exclamationmark.circle", "#fa0515"),
    INVALID_APPLE_IDENTITY_TOKEN_SIGNATURE(1008, HttpStatus.BAD_REQUEST, "Apple OAuth Identity Token 값이 올바르지 않습니다.", "제목", "메시지", "exclamationmark.circle", "#fa0515"),
    INVALID_APPLE_JWT(1009, HttpStatus.BAD_REQUEST, "Apple JWT 값의 alg, kid 정보가 올바르지 않습니다.", "제목", "메시지", "exclamationmark.circle", "#fa0515"),

    //user
    USER_NOT_FOUND(2001, HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다.", "제목", "메시지", "exclamationmark.circle", "#fa0515"),
    USER_ALREADY_EXISTS(2002, HttpStatus.BAD_REQUEST, "유저가 이미 존재합니다.", "제목", "메시지", "exclamationmark.circle", "#fa0515"),
    USER_ALREADY_CERTIFICATION(2003, HttpStatus.BAD_REQUEST, "이미 인증받은 사용자입니다.", "제목", "메시지", "exclamationmark.circle", "#fa0515"),

    //profile
    PROFILE_NOT_FOUND(3001, HttpStatus.NOT_FOUND, "프로필을 찾을 수 없습니다.", "제목", "메시지", "exclamationmark.circle", "#fa0515"),
    PROFILE_ALREADY_EXISTS(3002, HttpStatus.BAD_REQUEST, "프로필이 이미 존재합니다.", "제목", "메시지", "exclamationmark.circle", "#fa0515"),
    PROFILE_IMAGE_NOT_FOUND(3003, HttpStatus.NOT_FOUND, "프로필 이미지를 찾을 수 없습니다.", "제목", "메시지", "exclamationmark.circle", "#fa0515"),

    //experience
    EXPERIENCE_NOT_FOUND(4001, HttpStatus.NOT_FOUND, "반려동물 경험을 찾을 수 없습니다.", "제목", "메시지", "exclamationmark.circle", "#fa0515"),

    //category
    MAIN_CATEGORY_NOT_FOUND(4001, HttpStatus.NOT_FOUND, "메인 카테고리를 찾을 수 없습니다.", "제목", "메시지", "exclamationmark.circle", "#fa0515"),
    SUB_CATEGORY_NOT_FOUND(4002, HttpStatus.NOT_FOUND, "서브 카테고리를 찾을 수 없습니다.", "제목", "메시지", "exclamationmark.circle", "#fa0515"),

    //post
    POST_NOT_FOUND(5001, HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다.", "제목", "메시지", "exclamationmark.circle", "#fa0515"),
    POST_USER_NOT_MATCH(5002, HttpStatus.BAD_REQUEST, "게시글 작성자가 아닙니다.", "제목", "메시지", "exclamationmark.circle", "#fa0515"),
    POST_STATUS_COMPLETE(5003, HttpStatus.BAD_REQUEST, "이미 완료된 게시글입니다.", "제목", "메시지", "exclamationmark.circle", "#fa0515"),
    POST_STATUS_DELETE(5004, HttpStatus.BAD_REQUEST, "이미 삭제된 게시글입니다.", "제목", "메시지", "exclamationmark.circle", "#fa0515"),
    POST_STATUS_REPORT(5005, HttpStatus.BAD_REQUEST, "신고된 게시글입니다.", "제목", "메시지", "exclamationmark.circle", "#fa0515"),

    //apply
    APPLY_NOT_FOUND(6001, HttpStatus.NOT_FOUND, "지원서를 찾을 수 없습니다.", "제목", "메시지", "exclamationmark.circle", "#fa0515"),
    APPLY_ALREADY_EXISTS(6002, HttpStatus.BAD_REQUEST, "이미 지원서를 작성했습니다.", "제목", "메시지", "exclamationmark.circle", "#fa0515"),
    APPLY_CANT_MODIFY(6003, HttpStatus.BAD_REQUEST, "지원서를 수정할 수 없습니다.", "제목", "메시지", "exclamationmark.circle", "#fa0515"),

    //register
    REGISTER_NOT_FOUND(7001, HttpStatus.NOT_FOUND, "해당 반려동물 등록 정보가 없습니다.", "제목", "메시지", "exclamationmark.circle", "#fa0515"),
    REGISTER_ALREADY_EXISTS(7002, HttpStatus.BAD_REQUEST, "이미 등록했습니다.", "제목", "메시지", "exclamationmark.circle", "#fa0515"),
    REGISTER_NOT_MATCH_USER(7003, HttpStatus.BAD_REQUEST, "등록한 유저가 아닙니다.", "제목", "메시지", "exclamationmark.circle", "#fa0515"),
    //image
    S3_IMAGE_NOT_FOUND(8001, HttpStatus.NOT_FOUND, "S3 이미지를 찾을 수 없습니다.", "제목", "메시지", "exclamationmark.circle", "#fa0515"),
    IMAGE_NOT_FOUND(8002, HttpStatus.NOT_FOUND, "이미지를 찾을 수 없습니다.", "제목", "메시지", "exclamationmark.circle", "#fa0515"),

    //certification
    CERTIFICATION_NUMBER_EXPIRED(9001, HttpStatus.BAD_REQUEST, "인증번호가 만료되었습니다.", "제목", "메시지", "exclamationmark.circle", "#fa0515"),
    CERTIFICATION_NUMBER_NOT_MATCH(9002, HttpStatus.BAD_REQUEST, "인증번호가 일치하지 않습니다.", "제목", "메시지", "exclamationmark.circle", "#fa0515"),
    CERTIFICATION_PHONE_ALREADY_EXISTS(9003, HttpStatus.BAD_REQUEST, "이미 가입된 휴대전화입니다.", "제목", "메시지", "exclamationmark.circle", "#fa0515"),
    CERTIFICATION_NOT_FOUND(9004, HttpStatus.NOT_FOUND, "인증요청을 찾을 수 없습니다.", "제목", "메시지", "exclamationmark.circle", "#fa0515"),
    CERTIFICATION_NOT_COMPLETE(9005, HttpStatus.BAD_REQUEST, "인증이 완료되지 않았습니다.", "제목", "메시지", "exclamationmark.circle", "#fa0515"),
    CERTIFICATION_ATTEMPT_EXCEED(9006, HttpStatus.BAD_REQUEST, "인증 시도 횟수를 초과했습니다.", "제목", "메시지", "exclamationmark.circle", "#fa0515");

    private final int code;
    private final HttpStatus status;
    private final String message;
    private final String title;
    private final String detailMessage;
    private final String icon;
    private final String iconColor;

    ExceptionCode(int code, HttpStatus status, String message, String title, String detailMessage, String icon, String iconColor) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.title = title;
        this.detailMessage = detailMessage;
        this.icon = icon;
        this.iconColor = iconColor;
    }
}
