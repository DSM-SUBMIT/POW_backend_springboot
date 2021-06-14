package com.submitproject.POW_backend_springboot.security.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtTokenFilter extends GenericFilterBean { //사용자가 요청을 하면 필터를 거쳐서 컨트롤러로 감

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException { //요청과 응답을 동시에 받는다
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);

        if(token != null && jwtTokenProvider.validateToken(token)) { //토큰이 null이 아니고 && 유효하다면
            Authentication auth = jwtTokenProvider.getAuthentication(token); //인증 객체를 생성하고
            SecurityContextHolder.getContext().setAuthentication(auth); //보안관 친구(SecurityContextHolder)가방에 인증객체를 담아서
        }
        chain.doFilter(request, response); //적용된 필터와 함께 가방을 넘긴다.
    }

}
