package com.spring.mvc.chap05.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.RequestParam;

@Setter @Getter @ToString
@AllArgsConstructor
public class Page {
    private int pageNo;// 클라이언트가 보낸 페이지 번호
    private  int amount; // 클라이언트가 보낸 목록 게시물수


    public Page() {
        this.pageNo=1;
        this.amount=6;
    }



    /*
    만약에 한페이지에 게시물을 10개씩 뿌린다고 가정하면
    n페이지 (n-1)*m,m
    2페이지 10,10

     */

    public int getPageStart(){
        return (pageNo-1)*amount;
    }
}
