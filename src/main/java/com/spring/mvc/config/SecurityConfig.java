package com.spring.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {
    // 시큐리티 기본 설정(권한 처리, 초기 로그인 화면 없애기...)
    @Bean  //라이브러리 클래스 같은 내가 만들지 않은 객체를 등록해서 주입받기위한 아노테이션
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.csrf().disable() //csrf 토큰 공격을 방지하기 위한 장치 해제

                //모든 요청에 대해서 인증을 하지 않겠다.
                .authorizeRequests().antMatchers("/**").permitAll();

        return  http.build();

    }

    //패스워드 암호화 객체를 빈등록
    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
