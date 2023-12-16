package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.common.Search;
import com.spring.mvc.chap05.entity.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class BoardMapperTest {

    @Autowired
    BoardMapper mapper;

    //게시물을 300개저장
//    @Test
//    @DisplayName("게시물 300개를 저장해야한다")
//    void bulkInsertTest() {
//        //given
//        for (int i = 1; i <=300 ; i++) {
//
//            Board b = Board.builder()
//                    .content("테스트용 내용"+i)
//                    .title("테스트용 제목"+i)
//                    .build();
//            mapper.save(b);
//
//        }
//        //when
//        //
//
//
//        //then
//    }

    @Test
    @DisplayName("게시물 전체조회 -> 301개의  게시물이 조회됨")
    void findAlltest() {
        //given
        List<Board> boardList = mapper.findAll(new Search());

        //when

        //then
        assertEquals(301,boardList.size());
    }
    @Test
    @DisplayName("30번 게시물을 단일조회하면 제목에 27이 포함되어 있어야한다")
    void findOnetest() {
        //given
        int boardNo=30;
        //when
        Board b = mapper.findOne(30);

        //then
        assertTrue(b.getTitle().contains("29"));
    }

    @Test
    @DisplayName("29번 게시물을 삭제하고 다시 조회하면 조회되지 않아야한다")
    @Transactional
    @Rollback
    void deleteTest() {
        //테스트는 몇번을 돌려도 같은 결과가 나와야함.
        //given
        int boardNo=29;
        //when
        boolean b = mapper.deleteByNo(boardNo);
        Board one = mapper.findOne(boardNo);
        //then
        assertTrue(b);
        assertNull(one);
    }




}