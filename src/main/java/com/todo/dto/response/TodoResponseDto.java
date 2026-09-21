package com.todo.dto.response;

import com.todo.entity.Todo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class TodoResponseDto {

    private int id;

    private String title;

    private boolean isCompleted;

    private LocalDateTime createDate;

    @Builder
    public static TodoResponseDto from(Todo todo){

        return new TodoResponseDto(
                todo.getId(),
                todo.getTitle(),
                todo.isCompleted(),
                todo.getCreateDate()
        );

    }

}
