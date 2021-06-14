package com.submitproject.POW_backend_springboot.exception;

import com.submitproject.POW_backend_springboot.exception.handler.ErrorCode;
import com.submitproject.POW_backend_springboot.exception.handler.PowException;

public class ClubNotFoundException extends PowException {
    public ClubNotFoundException() {
        super(ErrorCode.ACCOUNT_NOT_FOUND);
    }
}
