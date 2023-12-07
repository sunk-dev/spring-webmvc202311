package com.spring.mvc.chap04.entity;

import com.spring.mvc.chap04.dto.ScoreRequestDto;
import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 엔터티 클래스
 * - 데이터 베이스에 저장할 데이터를 자바 클래스에 매칭
 */
@Setter
@Getter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

//create table tbl_score(
//
//        stu_num INT(10) primary key auto_increment,
//        stu_name varchar(255) not null ,
//        kor INT(3) not null ,
//        eng INT(3) not null ,
//        math INT(3) not null ,
//        total INT(3)  ,
//        average FLOAT(5,2) ,
//        grade CHAR(1)
//
//        );
//
//        select * from tbl_score;
public class Score {
    private  String name;//학생이름
    private  int kor,eng,math;//국영수 점수
    private int stuNum; //학번
    private  int total;//총점
    private  double average; //평균
    private  Grade grade ;//학점

    public Score(ScoreRequestDto score) {
        convertInputData(score);
        calculateTotalAndAverage();
        makeGrade();

    }

    public Score(ResultSet rs) throws SQLException {
        this.stuNum=rs.getInt("stu_num");
        this.name=rs.getString("stu_name");
        this.kor=rs.getInt("kor");
        this.eng=rs.getInt("eng");
        this.math=rs.getInt("math");
        this.total=rs.getInt("total");
        this.average=rs.getDouble("average");
        this.grade= Grade.valueOf(rs.getString("grade"));

    }

    private void makeGrade() {
        if(average>90){
            this.grade=Grade.A;
        } else if (average>=80) {
            this.grade=Grade.B;

        }
        else if (average>=70) {
            this.grade=Grade.C;

        }
        else if (average>=60) {
            this.grade=Grade.D;

        }else{
            this.grade=Grade.F;
        }
    }

    private void calculateTotalAndAverage() {
        this.total=kor+math+eng;
        this.average=total/3.0;
    }

    private void convertInputData(ScoreRequestDto score) {
        this.name= score.getName();
        this.kor= score.getKor();
        this.eng= score.getEng();
        this.math= score.getMath();
    }

    public void changeScore(ScoreRequestDto dto) {
        this.kor=dto.getKor();
        this.eng=dto.getEng();
        this.math= dto.getMath();
        calculateTotalAndAverage();
        makeGrade();
    }
}
