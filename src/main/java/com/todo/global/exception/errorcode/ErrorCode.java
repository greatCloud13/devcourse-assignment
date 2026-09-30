package com.todo.global.exception.errorcode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    USING_EMAIL(HttpStatus.CONFLICT, "이미 사용중인 이메일 입니다."),

    TODO_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "해당하는 ID의 Todo를 찾을 수 없습니다."),

    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "유효하지 않은 요청입니다"),

    UNAUTHENTICATED(HttpStatus.UNAUTHORIZED, "인증이 필요합니다."),

    INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "이메일 또는 비밀번호가 일치하지 않습니다."),

    ACCESS_DENIED(HttpStatus.FORBIDDEN, "접근 권한이 없습니다."),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 에러가 발생했습니다.");

    private final HttpStatus status;
    private final String message;
}
