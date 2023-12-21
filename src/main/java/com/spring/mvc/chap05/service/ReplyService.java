package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.common.Page;
import com.spring.mvc.chap05.common.PageMaker;
import com.spring.mvc.chap05.dto.request.ReplyPOSTRequestDTO;
import com.spring.mvc.chap05.dto.response.ReplyDetailResponseDTO;
import com.spring.mvc.chap05.dto.response.ReplyListResponseDTO;
import com.spring.mvc.chap05.entity.Reply;
import com.spring.mvc.chap05.repository.ReplyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReplyService {

    private final ReplyMapper replyMapper;


    // 댓글 목록 조회
    public ReplyListResponseDTO getList(long boardNo, Page page) {

        // DB에서 댓글 정보 조회
        List<ReplyDetailResponseDTO> replies = replyMapper.findAll(boardNo, page)
                .stream()
                .map(ReplyDetailResponseDTO::new)
                .collect(Collectors.toList());

        // DB에서 총 댓글 수 조회
        int count = replyMapper.count(boardNo);

        return ReplyListResponseDTO.builder()
                .replies(replies)
                .count(count)
                .pageInfo(new PageMaker(page,count))
                .build();
    }

    //댓글 등록서비스
    public ReplyListResponseDTO register(ReplyPOSTRequestDTO dto) throws  SQLException{

        log.debug("register services execute!!");

        //dto를 엔터티로 변환

        boolean flag = replyMapper.save(dto.toEntity());
        if(!flag){
            //등록되지 않으면
            log.warn("reply register failed!!");
            throw  new SQLException("댓글 저장 실패");
        }

        //등록이 성공하면 새롭게 갱신된 1페이지 댓글 내용을 재조회해서 응답한다.
        return getList(dto.getBno(),new Page(1,5));



    }

    //댓글 삭제
    // -> 댓글 한개 삭제 , 댓글번호로
    @Transactional //트랜잭션 처리, 하나라도 오류나면 알아서롤백
    public  ReplyListResponseDTO delete(long replyNo) throws Exception{
        //delete 하기전에 select 해야 조회됨

        Reply reply = replyMapper.findOne(replyNo);
        long boardNo = reply.getBoardNo();
        replyMapper.delete(replyNo);

       return getList(boardNo,new Page(1,5));


    }
}
