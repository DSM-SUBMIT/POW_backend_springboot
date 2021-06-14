package com.submitproject.POW_backend_springboot.security.jwt.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade {

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public String getClubId() {
        return this.getAuthentication().getName(); //토큰 만들 때 썼던
    }

    public boolean isLogin() {
        return getAuthentication() != null;
    }

}
