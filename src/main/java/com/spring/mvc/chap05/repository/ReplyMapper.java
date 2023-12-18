package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.common.Page;
import com.spring.mvc.chap05.entity.Reply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReplyMapper {

    //댓글 등록기능
    boolean save(Reply reply);
    //댓글 수정
    boolean  modify(Reply reply);
    //댓글 삭제
    boolean delete(long replyNo);
    //댓글 개별조회
    Reply findOne(long replyNo);

    //댓글 정체 목록조회
    //ex 3번 게시물의 모든댓글을 가져와
    //3번 게시물 번호 필요
    List<Reply> findAll(@Param("bn") long boardNo, @Param("p") Page page);

    //댓글총개수 조회
    int count(long boardNo);

}
