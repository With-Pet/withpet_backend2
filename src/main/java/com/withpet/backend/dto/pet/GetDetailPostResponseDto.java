package com.withpet.backend.dto.pet;

import com.withpet.backend.enumc.PetSex;
import com.withpet.backend.enumc.PetType;
import com.withpet.backend.enumc.PostType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class GetDetailPostResponseDto {
    private Long id;            //펫 고유번호
    private PetType petType;    //펫 타입 D : 강아지, C : 고영이, E : 기타
    private PostType postType;  //게시물 타입 W : 산책, C: 돌봄, E : 체험
    private Long weight;        //펫 몸무게
    private Long age;           //펫 나이
    private PetSex sex;         //펫 성별
    private Boolean isNeutralization; //펫 중성화 여부
    private LocalDateTime startDate;  //시작일
    private LocalDateTime endDate;    //종료일
    private Long price;          //시간당 비용
    private String specifics;    //특이 사항
    private String description;  //요청 사항
}
