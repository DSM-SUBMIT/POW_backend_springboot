package com.submitproject.POW_backend_springboot.security.jwt;

import com.submitproject.POW_backend_springboot.exception.InvalidTokenException;
import com.submitproject.POW_backend_springboot.security.jwt.auth.AuthDetails;
import com.submitproject.POW_backend_springboot.security.jwt.auth.AuthDetailsService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    @Value("${auth.jwt.secret}")
    private String secretKey;

    @Value("${auth.jwt.exp.access}")
    private Long accessTokenExpiration;

    @Value("${auth.jwt.header}")
    private String header;

    @Value("${auth.jwt.prefix}")
    private String prefix; // 이 안에 'bearer'이라는 문자열이 있음

    private final AuthDetailsService authDetailsService;

    @PostConstruct // 객체 생성될 때 호출됨
    private void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String generateAccessToken(Integer id) {
        return Jwts.builder()
                .setIssuedAt(new Date()) //생성일(현재 날짜 및 시간)
                .setSubject(id.toString()) //암호화할 문자열. 보통 pk값으로 설정
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpiration * 1000)) //시간을 밀리세컨드 단위로 알려줌(1초 = 1000밀리세컨드) / 단위 맞춰주기 위해서 *1000
                .claim("type", "access_token") //토큰의 유형 지정
                .signWith(SignatureAlgorithm.HS256, secretKey) //어떤 시크릿키로 암호화할 것인지 정해줌
                .compact(); //만들겠다!
    }

    public String resolveToken(HttpServletRequest request) { //HttpServletRequest는 http 요청 자체를 말함(header, body 다 있음)
        String bearerToken = request.getHeader(header); //key 값이 'authorization(header 변수 값)'인 value를 가져옴
        if (bearerToken != null && bearerToken.startsWith(prefix)) {
            return bearerToken.substring(7); //기본적으로 토큰 앞에 'bearer'이라는 문자가 들어감(규약) -> 그 단어를 제외한 실제 순수 토큰을 반환
        }
        return null;
    }

    public boolean validateToken(String token) { // 토큰이 유효한지 검사
        try {
            Jwts.parser().setSigningKey(secretKey) //환경 변수에 저장해 놓은 secretkey를 이용하여 해석
                    .parseClaimsJws(token).getBody().getSubject(); //body에서 고유 정보를 가져옴
            return true;
        } catch (Exception e) {
            throw new InvalidTokenException(); // token 형식이 잘못됐거나, secretkey가 잘못된 경우
        }
    }

    public String getId(String token) {
        try {
            return Jwts.parser().
                    setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            throw new InvalidTokenException();
        }
    }

    public Authentication getAuthentication(String token) { //토큰을 받아서 인증 객체를 만듦 / 여기서 만드는 인증 객체는 검증되지 않은 것
        AuthDetails authDetails = authDetailsService.loadUserByUsername(getId(token));
        return new UsernamePasswordAuthenticationToken(authDetails, "", authDetails.getAuthorities());
    }

}
