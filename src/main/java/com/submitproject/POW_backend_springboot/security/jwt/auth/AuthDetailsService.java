package com.submitproject.POW_backend_springboot.security.jwt.auth;

import com.submitproject.POW_backend_springboot.entity.club.ClubRepository;
import com.submitproject.POW_backend_springboot.exception.ClubNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthDetailsService implements UserDetailsService {

    private final ClubRepository clubRepository;

    @Override
    public AuthDetails loadUserByUsername(String id) throws UsernameNotFoundException { // 이거 만든 개발자는 고유값이 꼭 String일 줄 알았나봐..
        return clubRepository.findById(Integer.parseInt(id)) //pk 찾기
                .map(AuthDetails::new)
                .orElseThrow(ClubNotFoundException::new);
    }

}
