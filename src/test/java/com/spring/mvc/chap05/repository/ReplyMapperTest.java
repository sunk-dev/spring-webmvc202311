package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.common.Page;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.entity.Reply;
import org.apache.ibatis.annotations.Mapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional @Rollback
class ReplyMapperTest {
    @Autowired
    BoardMapper boardMapper;
    @Autowired
    ReplyMapper replyMapper;

    @Test
    @DisplayName("게시물 100개를 등록하고 랜덤으로 1000개의 댓글을 게시물에 등록함")
    void bulkInsertTest() {
        //given
        for (int i = 1; i <= 100; i++) {
            Board b = Board.builder()
                    .title("재미있는 글 "+i)
                    .content("fkfkfkkf "+i)
                    .build();
            boardMapper.save(b);



        }
        for (int i = 1; i <=1000 ; i++) {
            Reply r= Reply.builder()
                    .replyText("하하호호"+i)
                    .replyWriter("어린이"+i)
                    .boardNo((long)( Math.random()*100+1))
                    .build();
            replyMapper.save(r);


        }
        //when

        //then
    }
    @Test
    @DisplayName("77번 게시물의 댓글 목록을 조회했을때 결과 리스트의 사이즈는 8 이어야한다")
    void findAllTest() {
        //given
        long boardNo=77l;

        
        //when
        List<Reply> all = replyMapper.findAll(boardNo,new Page());
        //then
        assertEquals(8,all.size());
        assertEquals("어린이35",all.get(0).getReplyWriter());
    }
    @Test
    @DisplayName("77글의 185번 댓글 삭제 하면 185번 댓글을 조회되지 않을 것이고" +
            "77번을 전체조회하면 리스트 사이즈는 7 이어야함")
    void deleteTest() {
        //given
        long boardNo=77l;
        long replyNo=185l;
        //when
        replyMapper.delete(replyNo);
        Reply one = replyMapper.findOne(replyNo);
        //then
        assertNull(one);
        assertEquals(7,replyMapper.findAll(boardNo,new Page()).size());

    }

    @Test
    @DisplayName("461번 댓글 내용 수정하면 다시 조회했을때 수정된 내용이 조회된다")
    void modifyTest() {
        //given
        long replyNo=461l;
        String newReplyText="461번 수정댓글";
        //when
        Reply replyBuilder = Reply.builder().replyText(newReplyText).replyNo(replyNo). build();
        boolean modify = replyMapper.modify(replyBuilder);
        //then
        assertTrue(modify);
        Reply foundReply=replyMapper.findOne(replyNo);
        assertEquals(newReplyText,foundReply.getReplyText());
        System.out.println("\n\n\n\n\n");
        System.out.println("foundReply = " + foundReply);
    }

    
    
    
    



}