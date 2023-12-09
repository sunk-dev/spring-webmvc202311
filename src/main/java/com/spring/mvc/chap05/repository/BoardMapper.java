package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.entity.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper  {
    /////

    //목록조회
    List<Board> findAll();

    //상세조회
    Board findOne(int boardNo);

    //게시물 등록
    boolean save(Board board);

    //게시물 사삭데
    boolean deleteByNo(int boardNo);

    //조회수 상승 기능 처리
    //추가로
    void updateViewCount(int boardNo);
}
