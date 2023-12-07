package com.spring.mvc.chap06.spring_jdbc;

import com.spring.mvc.chap06.entity.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


//테스트 프레임 눠크 junit5 - 모든겅 default 제한자
@SpringBootTest   //스프링이 관리하는 빈을 주입받기 위한 아노테이션
class SpringJdbcRepositoryTest {

    @Autowired
    SpringJdbcRepository repository;

    @Test
    @DisplayName("사람 정보를 데이테베이스에 저장한다")
    void saveTest() {
        //given
        Person p = new Person("99", "철수", 30);
        //when
        repository.save(p);
        //then

    }

    @Test
    @DisplayName("99번 사람의 이름과 나이 정보를 수정함")
    void modifyTest() {
        //given
        String id="99";
        String newName="수정수정이";
        int newAge=50;
        Person p = new Person(id, newName, newAge);

        //when

        //then
        repository.modify(p);
    }

    @Test
    @DisplayName("99번 회원을 삭제")
    void removeTest() {

        //given
        String id="99";
        //when
        repository.remove(id);
        //then
    }

    @Test
    @DisplayName("전체 조회를 해야한다.")
    void findAllTest() {
        //given

        //when
        List<Person> people = repository.findtAll();
        //then
        people.forEach(System.out::println);
    }

    @Test
    @DisplayName("30번 회원을 등록 번한사람 조회")
    void finOneTest () {
        //given
        Person p = new Person("31", "두껍이", 23);

        //when
        repository.save(p);
        Person foundPerson = repository.findOne("31");
        //then
        assertEquals("두껍이",foundPerson.getPersonName());
        System.out.println("foundPerson = " + foundPerson);
    }









}