package com.spring.mvc.chap04.service;

import com.spring.mvc.chap04.dto.ScoreResponseDTO;
import com.spring.mvc.chap04.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 컨트롤러와 레파지 토리 사이에 위치해있으며
 * 중간 로직을 처리하는역할이다.
 *
 * 컨트롤러->서비스->레파지토리
 */

@RequiredArgsConstructor
@Service
public class ScoreService {

    private final ScoreRepository repository;


  //목록조회 중간처리
    /*
    컨트롤러는 데이터베이스에서 성적정보 리스트를
    조회해오길 원하고 있다.
    그런데 데이터베이스는 민감한 정보까지 모두 조회한다.
    그리고 컬럼명도 그재로 노출하기 때문에
    이모든것을 숨김처리 하고 싶다
     */

    public List<ScoreResponseDTO> getList(String sort) {
        return repository.findAll(sort)
                .stream()
                .map(ScoreResponseDTO::new)
                .collect(Collectors.toList());
    }}


