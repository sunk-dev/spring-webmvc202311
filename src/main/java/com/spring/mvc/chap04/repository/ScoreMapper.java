package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.entity.Score;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScoreMapper {

    List<Score> findAll(String sort);
    //성적정보 등록
    boolean save(Score score);
    //성적정보 1개 삭제
    boolean delete(int stuNum);
    //성적정보 개별 조회
    Score findOne(int stuNum);
}
