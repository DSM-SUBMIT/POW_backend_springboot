package com.submitproject.POW_backend_springboot.security.jwt.auth;

import com.submitproject.POW_backend_springboot.entity.club.Club;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@AllArgsConstructor
public class AuthDetails implements UserDetails {

    private final Club account;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() { //메소드명이 Username일 뿐, 사실은 고유값을 주겠다는 의미
        return account.getId().toString(); //계정의 이름을 반환한다는 것 == pk 반환
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
