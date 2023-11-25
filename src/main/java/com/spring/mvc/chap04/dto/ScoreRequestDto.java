package com.spring.mvc.chap04.dto;

import lombok.*;

//data transfer object
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ScoreRequestDto {
    private  String name;
    private int kor,eng,math;

}
