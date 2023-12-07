package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.entity.Board;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.util.comparator.Comparators;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class BoardRepositoryImpl implements BoardRepositoty {
    private static final Map<Integer ,Board> boardMap;
    //글번호 자동으로 증가시키기 위한 공유필드
    private  static  int sequence;

    static {
        boardMap=new HashMap<>();

        Board b1 = new Board(++sequence, "오늘은 금요일", "라라");
        Board b2 = new Board(++sequence, "오늘은 토요일", "마마");
        Board b3 = new Board(++sequence, "오늘은 일요일", "바바");

        boardMap.put(b1.getBoardNo(),b1);
        boardMap.put(b2.getBoardNo(),b2);
        boardMap.put(b3.getBoardNo(),b3);


    }

    @Override
    public List<Board> finaAll() {

        return boardMap.values()
                .stream()
                .sorted(Comparator.comparing(Board::getBoardNo).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Board findOne(int boardNo) {
        return boardMap.get(boardNo);
    }

    @Override
    public boolean save(Board board) {
        board.setBoardNo(++sequence);
        boardMap.put(board.getBoardNo(),board);
        return false;
    }


    @Override
    public boolean deleteByNo(int boardNo) {
        boardMap.remove(boardNo);
        return true;}
}