package com.todo.global.dto;

import ch.qos.logback.core.spi.ErrorCodes;
import com.todo.global.exception.errorcode.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {

    private final boolean success;
    private final T data;
    private final LocalDateTime time;
    private final ErrorDetail error;

    // 성공 응답 (데이터 있음)
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data, LocalDateTime.now(), null);
    }

    // 성공 응답 (데이터 없음 - ex. 삭제 성공)
    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(true, null, LocalDateTime.now(), null);
    }

    // 예외 응답
    public static <T> ApiResponse<T> error(String code, ErrorCode e) {
        return new ApiResponse<>(false, null, LocalDateTime.now() ,new ErrorDetail(e));
    }

    @Getter
    public static class ErrorDetail {

        private final int code;
        private final String message;

        public ErrorDetail(ErrorCode e){
            this.code = e.getStatus().value();
            this.message = e.getMessage();
        }

    }
}
