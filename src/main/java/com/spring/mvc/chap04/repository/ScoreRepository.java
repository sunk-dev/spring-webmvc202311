package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.entity.Score;

import java.util.List;

/**
 * 성적정보를 잘 저장하고 조회하고 수정하고 삭제하는 역할
 * 데이터 베이스와 같은 저장소에 접근하는 객체(Data Acess Object)
 *왜 인터페이스로 추상화 하는가? -> 실제로 저장소라는 개념은 구체적이지 않다!@
 *파일,인메모리,관계형db,noSql.....
 *
 *

 */


public interface ScoreRepository {
    //성적정보 전체 목록조회
    List<Score> findAll();
    //성적정보 등록
    boolean save(Score score);
    //성적정보 1개 삭제
    boolean delete(int stuNum);
    //성적정보 개별 조회
    Score findOne(int stuNum);


    //
}
