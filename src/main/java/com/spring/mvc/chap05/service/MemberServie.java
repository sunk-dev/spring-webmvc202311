package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.request.LoginRequestDTO;
import com.spring.mvc.chap05.dto.request.SignUpReauestDTO;
import com.spring.mvc.chap05.entity.Member;
import com.spring.mvc.chap05.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.spring.mvc.chap05.service.LoginResult.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServie {
    private final MemberMapper memberMapper;
    private final PasswordEncoder encoder;

    //회원 가입 처리 서비스
    public  boolean join(SignUpReauestDTO dto){

        //클라이언트가 보낸 회원가입 데이터를
        //패스워드 인코딩하여 엔터티로 변환해서 전달

        return memberMapper.save(dto.toEntity(encoder));

    }
    
    //로그인 검증
    public LoginResult authenticate(LoginRequestDTO dto){
        Member member = memberMapper.findMember(dto.getAccount());
        if(member==null){ //회원가입 안한상태
            log.info("{}- 회원 가입이 필요합니다 ",dto.getAccount());
            return NO_ACC;

        }
        //비밀번호 일치 검사
        String password=dto.getPassword(); //사용자 입력 패스 워드
        String realPassword= member.getPassword(); // 실제 패스 워드
        if(!encoder.matches(password,realPassword)){
            log.info("비밀번호가 일치하지 않습니다!");
            return NO_PW;

        }
        log.info("{}님 로그인 성공!",member.getAccount());
        return SUCCESS;
    }

}
