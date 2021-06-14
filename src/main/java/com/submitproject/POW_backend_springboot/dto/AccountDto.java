package com.submitproject.POW_backend_springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class AccountDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignInRequest {
        @NotBlank(message = "코드를 입력해 주세요.")
        private String code;

        @NotBlank(message = "비밀번호를 입력해 주세요.")
        private String password;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PasswordRequest {

        @NotNull
        private String existingPassword;

        @NotNull
        private String newPassword;
    }


}
