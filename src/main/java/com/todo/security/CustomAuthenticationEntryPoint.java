package com.todo.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.global.dto.ApiResponse;
import com.todo.global.exception.errorcode.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 로그인 없이 인증이 필요한 API 를 호출했을 때 실행됩니다.
 * 이 지점은 DispatcherServlet 이전(Security 필터 체인) 단계라
 * GlobalExceptionHandler(@RestControllerAdvice) 로는 잡히지 않아서,
 * 같은 ApiResponse 응답 모양을 여기서 직접 만들어줍니다.
 */
@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        ErrorCode errorCode = ErrorCode.UNAUTHENTICATED;

        response.setStatus(errorCode.getStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        objectMapper.writeValue(response.getWriter(), ApiResponse.error(errorCode));
    }
}