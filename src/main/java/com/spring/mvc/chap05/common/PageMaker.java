package com.spring.mvc.chap05.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter @ToString
@EqualsAndHashCode
public class PageMaker {
    //페이지 첫번호 끝번호(1,10-1,20..)
    private  int begin,end,finalPage;//finalPage 보정된 end
    private  boolean prev,next ,last;

    //현재 페이지 정보
    private Page page;
    //총게시물수
    private int totalCount;



    public PageMaker(Page page, int totalCount){
        this.page=page;
        this.totalCount=totalCount;
        makePageInfo();
    }

    //한화면에 보여질 페이지 수
    private static final  int PAGE_COUNT=10;
    //맨첫번째 페이지
    private static final int FIRST_PAGE=1;

    //페이지 생성에 필요한 데이터를 만드는 알고리즘
    private  void  makePageInfo(){
        //1.end 값 계산
        /**
         * 지금 사용자가 7페이지에 있다
         * -> 1-10구간을 만들어야함
         * 지금 사용자가 24페이지에 있다
         * ->21-30 구간을 만들어야함.
         * //5개씩 쪼개는 경우
         * 현재 13 -> 11-15
         */
        //공식:(올림(현재 사용자가 위치한 페이지넙버/한화면에 보여줄 페이지수))*한화면에 보여줄 페이지수
        //     위공식에 도출된 값을 올림처리함.
        this.end= (int) Math.ceil((double) page.getPageNo()/PAGE_COUNT)*PAGE_COUNT;

        //시작값 구하기
        this.begin=this.end-PAGE_COUNT+1;

        //3. prev 활성화여부
        /*
        1-10구간에는prev가 안보여도됨
        11-20 구간에는 이전으로 이동가능하므로 활성화
         */
        this.prev=begin>1;
        /**
         * end값 보정
         * - 마지막 구간 보정공식
         * 올림(총 게시물수/한페이지에 배치할 게시물수)
         * 237/10-=23.7->올림 24
         */

        this.finalPage=(int)Math.ceil((double) totalCount/page.getAmount());
        //마지막 페이지 구간에서 end값을 finalPage값으로 변셩
        if(this.finalPage<this.end){
            this.end=this.finalPage;
        }

        //4. next 활성화 여부- 마지막 페이지 구간에서만 비활성화
        this.next=this.end<this.finalPage;

        //5.맨 마지막 보여주는 버튼- 마지막 구간에서만 활성화
        this.last=this.end<this.finalPage;

    }
}
