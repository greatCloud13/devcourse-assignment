package com.todo.global.dto;

import com.todo.global.exception.errorcode.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
public class ErrorResponse {

    private int status;
    private String name;
    private String message;

    public static ResponseEntity<ApiResponse<?>> toResponseEntity(ErrorCode errorCode){
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(ApiResponse.error(errorCode));
    }

    public static ResponseEntity<ApiResponse<?>> toResponseEntity(ErrorCode errorCode, String customMessage){
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(ApiResponse.error(errorCode, customMessage));
    }

}
