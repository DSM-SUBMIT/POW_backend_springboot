package com.submitproject.POW_backend_springboot.exception;

import com.submitproject.POW_backend_springboot.exception.handler.ErrorCode;
import com.submitproject.POW_backend_springboot.exception.handler.PowException;

public class InvalidTokenException extends PowException {
    public InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }
}
