package com.todo.global.exception.errorcode;

import com.todo.global.exception.CustomException;

public class TodoNotFoundException extends CustomException {
    public TodoNotFoundException(){
        super(ErrorCode.TODO_NOT_FOUND_ERROR);}
}
