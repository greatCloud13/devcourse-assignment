package com.todo.service;

import com.todo.dto.request.TodoCreateDto;
import com.todo.dto.response.TodoResponseDto;
import com.todo.entity.Todo;
import com.todo.global.exception.errorcode.TodoNotFoundException;
import com.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoResponseDto createTodo(TodoCreateDto request){

        log.info("todo 생성 요청 내용: {}", request.getTitle());

        Todo todo =  new Todo(request.getTitle());

        log.info("todo 생성 내용: {}", todo.getTitle());

        todoRepository.save(todo);

        return TodoResponseDto.from(todo);
    }

    public Page<TodoResponseDto> getTodoPage(Pageable pageable){

        Page<Todo> todoPage = todoRepository.findAll(pageable);

        return todoPage.map(TodoResponseDto::from);
    }

    public TodoResponseDto getTodoById(int id){

        return TodoResponseDto.from(getTodo(id));
    }

    @Transactional
    public TodoResponseDto setTitle(int id, String title){

        Todo todo = getTodo(id);
        todo.setTitle(title);

        return TodoResponseDto.from(todo);
    }

    @Transactional
    public TodoResponseDto setComplete(int id){

        Todo todo = getTodo(id);
        todo.setCompleted();

        return TodoResponseDto.from(todo);
    }

    public TodoResponseDto setIncomplete(int id){

        Todo todo = getTodo(id);
        todo.setIncomplete();

        return TodoResponseDto.from(todo);
    }

    public void deleteTodo(int id){

        Todo todo = getTodo(id);
        todoRepository.delete(todo);

    }


    private Todo getTodo(int id){
        return todoRepository.findById(id).orElseThrow(TodoNotFoundException::new);
    }

}
