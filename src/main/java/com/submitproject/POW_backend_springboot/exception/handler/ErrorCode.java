package com.submitproject.POW_backend_springboot.exception.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
@Getter
@AllArgsConstructor
public enum ErrorCode {
    ACCOUNT_NOT_FOUND(404, "CLUB404-0", "Club account is not found"),
    INVALID_TOKEN(401, "CLUB401-0", "Invalid token"),
    INCORRECT_PASSWORD(400, "CLUB400-2", "Password information incorrect"),
    NOT_LOGIN(401, "CLUB401-2", "User is not logged in");

    private final int status;
    private final String code;
    private final String message;
}
