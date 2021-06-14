package com.submitproject.POW_backend_springboot.service.auth;

import com.submitproject.POW_backend_springboot.dto.AccountDto;
import com.submitproject.POW_backend_springboot.dto.TokenDto.*;
import com.submitproject.POW_backend_springboot.entity.club.ClubRepository;
import com.submitproject.POW_backend_springboot.exception.ClubNotFoundException;
import com.submitproject.POW_backend_springboot.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final ClubRepository clubRepository;

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public TokenResponse signIn(AccountDto.SignInRequest request) {
        return clubRepository.findByCode(request.getCode())
                .filter(club -> request.getPassword().equals(club.getPassword()))
                .map(clubRepository::save)
                .map(club -> {
                    String accessToken = jwtTokenProvider.generateAccessToken(club.getId());
                    return new TokenResponse(accessToken);
                })
                .orElseThrow(ClubNotFoundException::new);
    }

}
