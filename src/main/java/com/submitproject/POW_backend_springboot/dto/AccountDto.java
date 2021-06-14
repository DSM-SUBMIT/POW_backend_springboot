package com.submitproject.POW_backend_springboot.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
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
        @JsonProperty("existing_password")
        private String existingPassword;

        @NotNull
        @JsonProperty("new_password")
        private String newPassword;
    }


}
