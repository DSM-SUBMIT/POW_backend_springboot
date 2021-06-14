package com.submitproject.POW_backend_springboot.exception;

import com.submitproject.POW_backend_springboot.exception.handler.ErrorCode;
import com.submitproject.POW_backend_springboot.exception.handler.PowException;

public class NotLoggedInException extends PowException {
    public NotLoggedInException() {
        super(ErrorCode.NOT_LOGIN);
    }
}
