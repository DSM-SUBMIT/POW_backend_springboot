package com.submitproject.POW_backend_springboot.exception;

import com.submitproject.POW_backend_springboot.exception.handler.ErrorCode;
import com.submitproject.POW_backend_springboot.exception.handler.PowException;

public class PasswordIncorrectException extends PowException {
    public PasswordIncorrectException() {
        super(ErrorCode.INCORRECT_PASSWORD);
    }
}
