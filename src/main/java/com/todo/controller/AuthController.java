package com.todo.controller;

import com.todo.dto.request.LoginRequestDto;
import com.todo.dto.request.SignupRequestDto;
import com.todo.dto.response.UserDto;
import com.todo.global.dto.ApiResponse;
import com.todo.security.UserPrincipal;
import com.todo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserDto>> register(@RequestBody @Valid SignupRequestDto request){

        UserDto result = userService.register(request);

        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserDto>> login(@RequestBody @Valid LoginRequestDto request,
                                                      HttpServletRequest httpRequest) {

        // 이메일/비밀번호가 틀리면 AuthenticationException 이 던져지고
        // GlobalExceptionHandler.handleAuthenticationException 이 401로 응답
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

        // 세션에 SecurityContext 를 직접 저장 다음 요청부터 이 세션으로 인증 유지
        httpRequest.getSession(true)
                .setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);

        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        UserDto userDto = userService.getByEmail(principal.getEmail());

        return ResponseEntity.ok(ApiResponse.success(userDto));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(HttpServletRequest request) {
        new SecurityContextLogoutHandler()
                .logout(request, null, SecurityContextHolder.getContext().getAuthentication());
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok(ApiResponse.success());
    }
}