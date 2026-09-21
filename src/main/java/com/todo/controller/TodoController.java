package com.todo.controller;

import com.todo.dto.request.TodoCreateDto;
import com.todo.dto.request.TodoUpdateDto;
import com.todo.dto.response.TodoResponseDto;
import com.todo.global.dto.ApiResponse;
import com.todo.service.TodoService;
import jakarta.validation.Valid;
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
    public ResponseEntity<ApiResponse<TodoResponseDto>> createTodo(@Valid @RequestBody TodoCreateDto request){

        TodoResponseDto result = todoService.createTodo(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(result));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TodoResponseDto>> findById(@Valid @PathVariable int id){

        TodoResponseDto result = todoService.getTodoById(id);

        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<TodoResponseDto>>> findTodoPage(@Valid @ParameterObject Pageable pageable){

        Page<TodoResponseDto> result = todoService.getTodoPage(pageable);

        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<TodoResponseDto>> setTitle(@Valid @PathVariable int id, @Valid @RequestBody TodoUpdateDto request){

        TodoResponseDto result = todoService.setTitle(id, request.getTitle());

        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<ApiResponse<TodoResponseDto>> completeTodo(@Valid @PathVariable int id){

        TodoResponseDto result = todoService.setComplete(id);

        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @PatchMapping("/{id}/incomplete")
    public ResponseEntity<ApiResponse<TodoResponseDto>> incompleteTodo(@Valid @PathVariable int id){

        TodoResponseDto result = todoService.setIncomplete(id);

        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTodo(@Valid @PathVariable int id){

        todoService.deleteTodo(id);

        return ResponseEntity.ok(ApiResponse.success());
    }

}
