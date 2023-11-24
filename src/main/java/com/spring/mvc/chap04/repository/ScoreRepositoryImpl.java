package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.entity.Grade;
import com.spring.mvc.chap04.entity.Score;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
// ---IMpl ->인터페이스의 구체버전이라는뜻
public class ScoreRepositoryImpl implements ScoreRepository {

    //인메모리 저장공간 해시맵
    //키는 학번, 값은 성적 정보
    private static final Map<Integer,Score> scoreMap;
    //객체 초기화는 직접하는 것보다 주입받거내 생성자를 통해 처리하는게 좋다
    static {
        scoreMap=new HashMap<>();
        Score s1 = new Score("뽀로로", 100, 88, 33, 1, 0, 0.0, Grade.F);
        Score s2 = new Score("춘식이", 33, 99, 22, 2, 0, 0.0, Grade.F);
        Score s3 = new Score("쿠로미", 55, 66, 77, 3, 0, 0.0, Grade.F);

        scoreMap.put(s1.getStuNum(),s1);
        scoreMap.put(s2.getStuNum(),s2);
        scoreMap.put(s3.getStuNum(),s3);

    }
    @Override
    public List<Score> findAll() {
//        List<Score> temp=new ArrayList<>();
//        for (Integer key : scoreMap.keySet()) {
//            Score score = scoreMap.get(key);
//            temp.add(score);
//
//
//        }
        return new ArrayList<>(scoreMap.values())
                .stream()
                .sorted(Comparator.comparing((Score s)-> s.getStuNum()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean save(Score score)
    {
        if(scoreMap.containsKey(score.getStuNum()))return false;
        scoreMap.put((score.getStuNum()),score);
        return true;
    }

    @Override
    public boolean delete(int stuNum) {
        //없는 학번을 전달 받는 경우

        if(!scoreMap.containsKey(stuNum)) return false;
        scoreMap.remove(stuNum);

        return true;
    }

    @Override
    public Score findOne(int stuNum) {
        return scoreMap.get(stuNum);

    }
}
