package com.todo.controller;

import com.todo.dto.request.TodoCreateDto;
import com.todo.dto.response.TodoResponseDto;
import com.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/todo")
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoResponseDto> createTodo(TodoCreateDto request){

        TodoResponseDto result = todoService.createTodo(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDto> findById(@PathVariable Integer id){

        TodoResponseDto result = todoService.getTodoById(id);

        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<Page<TodoResponseDto>> findTodoPage(@ParameterObject Pageable pageable){

        Page<TodoResponseDto> result = todoService.getTodoPage(pageable);

        return ResponseEntity.ok(result);
    }

//    Todo: 수정 기능 구현

//    Todo: 완료 기능 구현

//    Todo: 삭제 기능 구현

}
