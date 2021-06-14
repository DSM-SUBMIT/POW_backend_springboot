package com.submitproject.POW_backend_springboot.exception.handler;

import lombok.Getter;

@Getter
public class PowException extends RuntimeException {

    private final ErrorCode errorCode;

    public PowException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

}
