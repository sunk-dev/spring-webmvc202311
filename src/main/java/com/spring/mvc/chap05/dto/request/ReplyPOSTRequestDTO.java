package com.spring.mvc.chap05.dto.request;

import com.spring.mvc.chap05.entity.Reply;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter @ToString@EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ReplyPOSTRequestDTO {
    
    //NotgBlank :null 빈문자열 둘다안됨
    //Notnull"null만 안됨 빈문자열 됨
    @NotBlank @Size(min = 1,max = 300)
    private String text;
    @NotBlank @Size(min = 2,max = 8)
    private String author; //클라이언트가 보낸대로
    @NotNull
    private Long bno; //원본 글번호

    //dto를 엔터티로 바꾸는 변환 메서드

    public Reply toEntity(){
        return Reply.builder()
                .replyText(this.text)
                .replyWriter(this.author)
                .boardNo(this.bno)
                .build();
    }
}
