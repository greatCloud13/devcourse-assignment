package com.todo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String email;
    private String nickname;
    private LocalDateTime createDate;

}
