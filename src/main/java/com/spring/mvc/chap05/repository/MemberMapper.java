package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {
    // 회원가입기능

    boolean save(Member member);
    //회원정보 단일 조회
    Member findMember(String account);



    /**
     *
    // 중복체크(account,email) 기능
     * @param type -중복을 검사할 내용 account, email
     *
     * @param keyword- 중복검사 입력값 ex abc@gmail.com
     * @return- 중복이면 true 아니면 false
     */

    boolean isDuplicate(@Param("type") String type, @Param("keyword") String keyword);


}
