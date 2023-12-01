package com.spring.mvc.chap05.repository;


import com.spring.mvc.chap05.entity.Board;

import java.util.List;

//게시판 CRUD 기능 명세
public interface BoardRepositoty {

    //목록조회
    List<Board> finaAll();

    //상세조회
    Board findOne(int boardNo);

    //게시물 등록
    boolean save(Board board);

    //게시물 사삭데
    boolean deleteByNo(int boardNo);

}
