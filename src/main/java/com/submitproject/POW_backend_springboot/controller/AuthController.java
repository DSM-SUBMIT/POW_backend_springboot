package com.submitproject.POW_backend_springboot.controller;

import com.submitproject.POW_backend_springboot.dto.AccountDto.*;
import com.submitproject.POW_backend_springboot.dto.TokenDto.*;
import com.submitproject.POW_backend_springboot.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/auth")
@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TokenResponse signIn(@RequestBody @Valid SignInRequest request) {
        return authService.signIn(request);
    }

}
