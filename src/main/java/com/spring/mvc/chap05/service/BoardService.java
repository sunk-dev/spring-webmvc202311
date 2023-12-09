package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.BoardDetailResponseDTO;
import com.spring.mvc.chap05.dto.BoardListResponseDTO;
import com.spring.mvc.chap05.dto.BoardWriteRequestDTO;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    
    private  final BoardRepository boardRepositoty;
    
    //목록조회 중간처리
    public List<BoardListResponseDTO> getList(){
        return boardRepositoty.finaAll()
                .stream()
                .map(BoardListResponseDTO::new)
                .collect(Collectors.toList());
    }

    //글쓰기 중간처리
    public void register(BoardWriteRequestDTO dto) {
        //dto를 엔터티로 변환

        Board board = new Board(dto);
        boardRepositoty.save(board);

    }

    public void delete(int bno) {
        boardRepositoty.deleteByNo(bno);
    }

    public BoardDetailResponseDTO getDetail(int bno) {
        Board board = boardRepositoty.findOne(bno);
        //조회수 상승처리-> 이름을 좀더 명확하게 만들기
        boardRepositoty.updateViewCount(bno);

        return new BoardDetailResponseDTO(board);
        
    }
}

