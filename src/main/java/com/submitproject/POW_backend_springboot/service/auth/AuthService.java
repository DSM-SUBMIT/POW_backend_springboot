package com.submitproject.POW_backend_springboot.service.auth;

import com.submitproject.POW_backend_springboot.dto.AccountDto.*;
import com.submitproject.POW_backend_springboot.dto.TokenDto.*;

public interface AuthService {
    TokenResponse signIn(SignInRequest request);
}
