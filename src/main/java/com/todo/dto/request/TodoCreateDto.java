package com.todo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TodoCreateDto {

    @NotBlank(message = "todo 제목이 비어있거나 공백입니다.")
    @Size(min =1, max = 30, message = "이름은 1자이상 20자 이내여야 합니다.")
    private String title;

}
