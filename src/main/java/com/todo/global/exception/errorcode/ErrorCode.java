package com.todo.global.exception.errorcode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    TODO_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "해당하는 ID의 Todo를 찾을 수 없습니다."),

    // 500 INTERNAL_SERVER_ERROR 서버 내부 에러
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 에러가 발생했습니다.");

    private final HttpStatus status;
    private final String message;
}
