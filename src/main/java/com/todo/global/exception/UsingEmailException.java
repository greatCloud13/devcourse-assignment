package com.todo.global.exception;

import com.todo.global.exception.errorcode.ErrorCode;

public class UsingEmailException extends CustomException{
    public UsingEmailException() {
        super(ErrorCode.USING_EMAIL);
    }
}
