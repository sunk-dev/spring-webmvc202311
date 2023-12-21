package com.spring.mvc.chap05.api;

import com.spring.mvc.chap05.common.Page;
import com.spring.mvc.chap05.dto.request.ReplyPOSTRequestDTO;
import com.spring.mvc.chap05.dto.response.ReplyDetailResponseDTO;
import com.spring.mvc.chap05.dto.response.ReplyListResponseDTO;
import com.spring.mvc.chap05.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

/**
 * RESTAPI URL 설계원칙
 * - crud는 url에 명시하는게 아니라 HTTP method 로만 표시해야함
 * => /replies/write(x)
 * -> /replies: POST(0)
 * => /replies/all (x)
 * => /replies :GET(0)
 *
 * => /replies/17 :GET - 단일 조회
 *
 * => /replies/3  :DELETE
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/replies")
public class ReplyApiController {

    private final ReplyService replyService;

    //댓글 목록 조회 요청
    // URL: /api/v1/replies/글번호/page/페이지번호
    @GetMapping("/{boardNo}/page/{pageNo}")
    public ResponseEntity<?> list(
            @PathVariable  long boardNo,
            @PathVariable int pageNo
            ){

        log.info("/api/v1/replies/{}/page/{}:GET!!",boardNo,pageNo);
        Page page = new Page();
        page.setPageNo(pageNo);
        page.setAmount(5);
        ReplyListResponseDTO replies= replyService.getList(boardNo,page);
        return  ResponseEntity.ok().body(replies);
    }


    //댓글 등록 요청처리
    //@RequestParm :동기 요청에서 ? 뒤에 있는 요청처리
    //RequsetBody: 비동기 요청에서 메시지 안에 있는 json파싱
    @PostMapping
    public ResponseEntity<?> create(@Validated @RequestBody ReplyPOSTRequestDTO dto
                                    , BindingResult result){
        
        //입력값 검증에 걸리면 400번 코드와 함께 메시지를 클라이언트에 전송
        
        if(result.hasErrors()){
            return ResponseEntity
                    .badRequest()
                    .body(result.toString());
        }
        
        
        log.info("/api/v1/replies :POST");
        log.info("request parmater:{}",dto);
        try {
            ReplyListResponseDTO reponseDTO = replyService.register(dto);
            return ResponseEntity.ok().body(reponseDTO);
        } catch (SQLException e) {
            log.warn("500 status code response!! cause by {}",e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }

    //댓글 삭제 요청 처리
    @DeleteMapping("/{replyNo}")
    public ResponseEntity<?> remove(
            @PathVariable Long replyNo
    ){
        if(replyNo==null){
            return ResponseEntity
                    .badRequest()
                    .body("댓글 번호를 본내주세요!");

        }
        log.info("/api/v1/replies/{} : DELETE",replyNo);
        try {
            ReplyListResponseDTO responseDTO = replyService.delete(replyNo);
            return ResponseEntity
                    .ok()
                    .body(responseDTO);
        }catch (Exception e){
            return ResponseEntity
                    .internalServerError()
                    .body(e.getMessage());

        }
    }

}
