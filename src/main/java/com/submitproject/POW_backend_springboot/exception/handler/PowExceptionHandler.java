package com.submitproject.POW_backend_springboot.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PowExceptionHandler { //바로 RuntimeException을 사용하면 예외처리가 광범위해 지므로, handler를 만들어서 관리한다.

    @ExceptionHandler(PowException.class)
    protected ResponseEntity<ErrorResponse> handleMunchkinException(final PowException e) {
        final ErrorCode errorCode = e.getErrorCode();
        return new ResponseEntity<>(new ErrorResponse(errorCode.getStatus(), errorCode.getCode(), errorCode.getMessage()),
                HttpStatus.valueOf(errorCode.getStatus()));
    }

}
