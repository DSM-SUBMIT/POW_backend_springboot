package com.submitproject.POW_backend_springboot.security.config;

import com.submitproject.POW_backend_springboot.security.jwt.JwtConfigurer;
import com.submitproject.POW_backend_springboot.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()//basic auth를 사용하기 위해 csrf 보호 기능 disable
                .cors().and()
                .sessionManagement().disable()
                .formLogin().disable()
                .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/auth").permitAll()
                    .antMatchers(HttpMethod.GET, "/club/search/**").permitAll()
                    .antMatchers(HttpMethod.GET, "/club").permitAll()
                    .antMatchers(HttpMethod.GET,"/clubpage/**").permitAll()
                .anyRequest().authenticated() //위에 설정되지 않은 다른 api는 모두 토큰 인증이 필요함 //permitAll이 안되어있는데 객체가 없으면 403
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") //요청된 모든 도메인 이름이 도메인 간 리소스에 접근할 수 있도록 허용한다.
                .allowedOrigins("*")
                .allowedMethods("*"); //모든 메소드로 설정
    }

}
