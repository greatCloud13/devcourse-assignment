package com.todo.global.exception;


import com.todo.global.dto.ApiResponse;
import com.todo.global.dto.ErrorResponse;
import com.todo.global.exception.errorcode.ErrorCode;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * @Valid 검증 실패 시 발생 (@RequestBody DTO 검증 실패)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ApiResponse<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

//      첫번째 에러 메시지만 가져옴
        FieldError fieldError = e.getBindingResult().getFieldError();

        // fieldError가 null일 경우
        String errorMessage = "유효하지 않은 요청 데이터입니다.";
        if (fieldError != null) {
            errorMessage = String.format("%s", fieldError.getDefaultMessage());
        }

        log.error("handleMethodArgumentNotValidException: {}", errorMessage);

        return ErrorResponse.toResponseEntity(ErrorCode.INVALID_INPUT_VALUE, errorMessage);
    }

    /**
     * @Validated 검증 실패 시 발생 (@PathVariable, @RequestParam 검증 실패)
     */
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<ApiResponse<?>> handleConstraintViolationException(ConstraintViolationException e) {
        log.error("handleConstraintViolationException: {}", e.getMessage());

        return ErrorResponse.toResponseEntity(ErrorCode.INVALID_INPUT_VALUE);
    }


    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ApiResponse<?>> handleCustomException(CustomException e){
        log.error("handleCustomException throw CustomException: {}", e.getErrorCode());
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ApiResponse<?>> handleException(Exception e){
        log.error("handleException throw Exception : {}", e.getMessage());
        return ErrorResponse.toResponseEntity(ErrorCode.INTERNAL_SERVER_ERROR);
    }

}
