package com.spring.mvc.chap05.entity;

import lombok.*;

import java.time.LocalDateTime;

//-- 회원 관리 테이블
//        CREATE TABLE tbl_member (
//        account VARCHAR(50),
//        password VARCHAR(150) NOT NULL,
//        name VARCHAR(50) NOT NULL,
//        email VARCHAR(100) NOT NULL UNIQUE,
//        auth VARCHAR(20) DEFAULT 'COMMON',
//        reg_date DATETIME DEFAULT current_timestamp,
//        CONSTRAINT pk_member PRIMARY KEY (account)
//        );


//DB에 처리할때 사용하는 =>  테이블구조랑 똑같이 만들어야함

@Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    private  String account;
    private String password;
    private String name;
    private String email;
    private Auth auth;
    private LocalDateTime regDate;
}
