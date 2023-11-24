package com.spring.mvc.chap04.controller;

import com.spring.mvc.chap04.entity.Score;
import com.spring.mvc.chap04.repository.ScoreRepository;
import com.spring.mvc.chap04.repository.ScoreRepositoryImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * #컨트롤러
 * - 클라이언트의 요청을 받아서 처리하고 응답을 제공하는 객체
 *
 * # 요청 URL Endpoint
 * 1. 학생의 성적 정보 등록폼 화면을 보여주고
 *    동시에 지금까지 저장되어 있는 성적정보 목록을 조회
 *    - score/list :GET
 *  2. 학생의 입력된 성적정보를데이터베이스에 저장하는 요청
 *     - score/register : POST
 *  3. 성적 정보를 삭제하는 요청
 *     - /score/remove : GET or POST
 *  4. 성적정보 상세 조회 요청
 *    - /score/detail :GET
 *  5.
 */

@Controller
@RequestMapping("/score")
//@RequiredArgsConstructor //final이 붙은 필드를 초기화하는 생성자를 생성함.
@AllArgsConstructor //모든 필드를 초기화하는 생성자를 생성
public class ScoreController {

//    저장소에 의존하여 데이터 처리를 위인한다.
    //의존객체는 불변성을 가지는 것이 좋다.
    private final ScoreRepository repository;

//    @Autowired //스프링에 등록된 빈을 자동주입
//    //생성자 주입을 사용하고 생성자가 단 하나 일때 ->autoWired 생략가능
//    public ScoreController(ScoreRepository repository){
//        this.repository=repository;
//
//    }
//
    //1. 성적 폼 띄우기+목록조회
    //jsp 파일로 입력폼 화면을 띄워줘야함(viw forwarding)
    //-저장된 성적정보 리스트를 jsp에 보내줘야함(Model 데이터 전송
    // - 저장된 성적정보 리스트를 어떻게 가져오느냐 from 데이터 베이스
    //-
    @GetMapping("/list")
    public String list(Model model){
        System.out.println("/score/list GET");
        List<Score> scoreList = repository.findAll();
        System.out.println("scoreList = " + scoreList);
        model.addAttribute("sList",scoreList);


        return "";
    }

    //1. 성적 폼 띄우기+목록조회
    @PostMapping("/register")
    public String register(){
        System.out.println("/score/list GET");

        return "";
    }

    //3. 성적삭제 요청
    //RequestMapping-getpost 둘다받을때
    @RequestMapping(value = "/remove",method = RequestMethod.GET)
    public String remove(HttpServletRequest http){
        System.out.println("/score/list  GET");


        return "";
    }

    //1. 성적 폼 띄우기+목록조회
    @GetMapping("/detail")
    public String detail(){
        System.out.println("/score/detail GET");

        return "";
    }

}
