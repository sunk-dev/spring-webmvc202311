package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.request.LoginRequestDTO;
import com.spring.mvc.chap05.dto.request.SignUpReauestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.spring.mvc.chap05.service.LoginResult.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServieTest {

    @Autowired
    MemberServie memberServie;

    @Test
    @DisplayName("회원 정보를 전달하면 비밀번호가 암호화 되어 디비에 저장된다")
    void joinTest() {
        //given
        SignUpReauestDTO dto = SignUpReauestDTO.builder()
                .account("kitty")
                .password("kkk1234!")
                .name("헬로키티")
                .email("sanrio@gmail.com")
                                .build();
        //when
        boolean flag = memberServie.join(dto);
        //then
        assertTrue(flag);
    }

    @Test
    @DisplayName("계정명이 kitty 인 회원의 로그인 시도 결과를 상황별로 검증한다")
    void loginTest() {
        //given
        LoginRequestDTO dto = LoginRequestDTO.builder()
                .account("kitty")
                .password("kkk1234!")
                .build();
        //when
        LoginResult authenticate = memberServie.authenticate(dto);

        //then
        assertEquals(NO_PW,authenticate);
    }


}