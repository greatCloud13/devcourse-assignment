package com.todo.controller;

import com.todo.dto.request.SignupRequestDto;
import com.todo.dto.response.UserDto;
import com.todo.global.dto.ApiResponse;
import com.todo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserDto>> register(@RequestBody @Valid SignupRequestDto request){

        UserDto result = userService.register(request);

        return ResponseEntity.ok(ApiResponse.success(result));
    }


}
