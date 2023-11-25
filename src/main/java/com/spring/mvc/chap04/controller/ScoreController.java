package com.spring.mvc.chap04.controller;

import com.spring.mvc.chap04.dto.ScoreRequestDto;
import com.spring.mvc.chap04.dto.ScoreResponseDTO;
import com.spring.mvc.chap04.entity.Score;
import com.spring.mvc.chap04.repository.ScoreRepository;
import com.spring.mvc.chap04.service.ScoreService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

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
//    private final ScoreRepository repository;
    private  final ScoreService service;

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
    public String list(Model model,@RequestParam(defaultValue = "num") String sort){
        System.out.println("/score/list GET");

        //db에서 조회한 모든 데이터
//        List<Score> scoreList = repository.findAll(sort);
//        System.out.println("scoreList = " + scoreList);
//
        //클라이언트가 필요한 일부데이터
//

        //데이터를 클라이언트에 보낼때 데이터를 정제하고 있음.

//        List<ScoreResponseDTO> dtoList = scoreList.stream()
//                .map(ScoreResponseDTO::new)
//                .collect(Collectors.toList());

        List<ScoreResponseDTO> dtoList = service.getList(sort);

        model.addAttribute("sList",dtoList);



        return "chap04/score-list";
    }

    //2.성적정보를 데이터 베이스에 저장하는 요청
    @PostMapping("/register")
    public String register(ScoreRequestDto score){
        System.out.println("/score/list GET");
        System.out.println("score = " + score);

        //dto를 엔터티로 변환->데이터 생성
        service.insertScore(score);

        /**
         * forward vs redirect
         * - 포워드는 요청 리소스를 그대로 전달해줌
         * - 따라서 URL이 변경되지 않고 한번의 요청과 한번의 응답만 이루어짐
         * - 리다이렉트는 요청후에 자동응답이 나가고
         *   두번째 자동 요청이 들어옴 2번째 응답을 내보낸다
         *   두번재 요청의 URL로 자동 변경됨.
         */

        //주의사한 forward 할때는 포워딩할 파일의 경로를 적는것
        // ex) /WEB_INF/~~ /score-list.jsp

        //redirect 할때는 요청 URL을 적는것
        return "redirect:/score/list";
    }

    //3. 성적삭제 요청
    //RequestMapping-getpost 둘다받을때
    @RequestMapping(value = "/remove/{stuNum}",method = RequestMethod.GET)
    public String remove(HttpServletRequest request,
                         @PathVariable int stuNum){

        System.out.println("삭제학번"+stuNum);
        service.deleteScore(stuNum);

        return "redirect:/score/list";
    }

    //1. 성적 폼 띄우기+목록조회
    //@RequestParam-변수이름과 파라미터 이름같으면 생략가능
    @GetMapping("/detail")
    public String detail(@RequestParam int stuNum,Model model){
        System.out.println("/score/detail GET");
        retrieve(stuNum, model);

        return "chap04/score-detail";
    }

    //5.수정 입력폼을 열어주는 요청

    // /score/modifiy:GET

    @GetMapping("modify")
    public  String modify(int stuNum,Model model){
        System.out.println("/score/modify GET");
        retrieve(stuNum, model);
        return "chap04/score-modify";
    }

    private void retrieve(int stuNum, Model model) {
        Score score = service.retrieve(stuNum);
        model.addAttribute("s",score);
    }

    //6. 수정처리 요청
    // /score/modify :POST
    @PostMapping ("modify")
    public  String modify(int stuNum, ScoreRequestDto dto){
        System.out.println("/score/modify GET");
       service.updateScore(stuNum,dto);

        return "redirect:/score/detail?stuNum="+stuNum;
    }

}
