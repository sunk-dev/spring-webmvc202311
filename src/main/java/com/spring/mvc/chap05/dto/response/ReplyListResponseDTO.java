package com.spring.mvc.chap05.dto.response;

import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Builder
public class ReplyListResponseDTO {
    private int count; //총댓글수 json에서도 위에서 배치됨
    private List<ReplyDetailResponseDTO> replies;//실제 댓글 리스트


}
