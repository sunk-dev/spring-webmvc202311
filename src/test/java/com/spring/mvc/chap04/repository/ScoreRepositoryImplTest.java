package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.entity.Grade;
import com.spring.mvc.chap04.entity.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoreRepositoryImplTest {

    ScoreRepository repository = new ScoreRepositoryImpl();

    //단위 테스트 (Unit Test)
    //junit5
    //테스트 시나리오  a를 주면 b가 나온다.
    //단언 기법(Assertion)
    //GwT given when then
    @Test
    @DisplayName("저장소에서 findAll메서드를 호출하면" + "리스트가 반환되고, 해당 리스트에는 성적정보가 3개 들어있어야한다.")
    void findAllTest() {
        //gwt패턴
        //given 테스트를 위해 주어지는 데이터-parameter
        //when:테스트 해봐야할 사항
        List<Score> scoreList = repository.findAll();


        //then :테스트 결과 단언(결과 확인)
        scoreList.forEach(System.out::println);
        assertEquals(3, scoreList.size());
        assertNotNull(scoreList);
        assertEquals("뽀로로", scoreList.get(0).getName());

    }

    @Test
    @DisplayName("저장소에거 findOne을 호출하여 학번이 2인 학생을 조회하면 " +
            "그학생의 국어점수가 33점 이고 이름은 춘식이어야한다.")
    void findOneTest() {
        //given

        int stuNum = 2;
        //when
        Score score = repository.findOne(stuNum);
        //then
        assertEquals(33, score.getKor());
        assertEquals("춘식이", score.getName());

    }

    @Test
    @DisplayName("학번이 -99번인 학생을 조회하면 Null이 나와야한다.")
    void FindOneFaillTest() {
        int stuNum = -99;
        Score score = repository.findOne(stuNum);
        assertNull(score);
    }

    @Test
    @DisplayName("저장소에서 학번이 2인 학생을 삭제한 후에" +
            "리스트를 전체조회보면 성적의 개수가 2개일 것이고" +
            "다시 2번학생을 조회했을 때 null이 반환되어야 한다.")
    void deleteTest() {

        int stuNum = 2;
        //given


        boolean flag = repository.delete(stuNum);
        List<Score> scoreList = repository.findAll();
        Score score = repository.findOne(stuNum);

        //then
        assertTrue(flag);
        assertEquals(2, scoreList.size());
        assertNull(score);

    }

    @Test
    @DisplayName("새로운 성적정보를 save를 통해 추가하면" +
            "목록의 개수가 4개여야 한다.")
    void saveTest() {

        //given
        Score score = new Score("라라", 188, 283, 34, 4, 0, 0.0, Grade.A);
        //when
        boolean flag = repository.save(score);
        List<Score> scoreList = repository.findAll();
        //then
        assertTrue(flag);
        assertEquals(4, scoreList.size());

    }


}