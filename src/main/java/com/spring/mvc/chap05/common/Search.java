package com.spring.mvc.chap05.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.RequestParam;

@Setter @Getter
@ToString
@EqualsAndHashCode
public class Search extends Page {

    //검색조건, 검색어
     private  String type,keyword;

    public Search() {
        //null방지
        this.type="";
        this.keyword="";

    }





}
