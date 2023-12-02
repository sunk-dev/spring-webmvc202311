package com.spring.mvc.chap06.jdbc;

import com.spring.mvc.chap06.entity.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JdbcRepositoryTest {
    @Autowired
    JdbcRepository repository;

    @Test
    @DisplayName("데이터 베이스 접속에 성공해야한다.")
    void connectTest() {
        try {
            Connection connection = repository.getConnection();
            assertNotNull(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("사람 객체 정보를 데이터 베이스에 삽입해야한다")
    void saveTest() {
        Person person = new Person("1", "망둥이", 10);

        //세이브 상황
        repository.save(person);
        //then
    }

    @Test
    @DisplayName("회원번호가 1인 회원의 이름과 나이를 수정해야한다")
    void updateTest() {
        //given
        String id = "1";
        String newName = "개굴이";
        int newAge = 15;

        //when
        Person person = new Person(id, newName, newAge);
        repository.update(person);
    }

    @Test
    @DisplayName("삭제")
    void deleteTest() {
        String delete_id = "1";
        repository.delete(delete_id);
    }

    @Test
    @DisplayName("랜덤 화인 아이디 10면 등록")
    void bulkInsertTset() {

        for (int i = 0; i < 10; i++) {
            Person person = new Person("" + i, "" + Math.random(), i+5);
            repository.save(person);

        }

    }

    @Test
    @DisplayName("랜덤 화인 아이디 10면 등록")
    void findAllTest() {
        List<Person> all = repository.findAll();

        System.out.println("all = " + all);

    }

    @Test
    @DisplayName("특정 하이디 회원을 조회하면 하나의 회원만 조회된다")
    void findOneTest(){

        String id="7";
        Person one = repository.findOne(id);
        System.out.println("one = " + one);

    }

}