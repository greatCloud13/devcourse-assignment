package com.todo.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequestDto {

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 유효성 검증에 실패하였습니다.")
    private String email;

    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    @Size(min = 8, max = 20, message = "닉네임은 8자 이상 20자 이내여야합니다.")
    private String nickname;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Size(min = 12, message = "비밀번호는 12자 이상이여야합니다.")
    private String password;

}
