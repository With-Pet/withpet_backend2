package com.withpet.backend.dto;

import com.withpet.backend.enumc.PetSex;
import com.withpet.backend.enumc.PetType;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 자신의 펫 등록용 dto
 */
@Data
public class RegisterPetDto {
    private String name;    //펫 이름
    private LocalDateTime birth; //펫 생일
    private PetType type;   //펫 타입 D : 강아지, C : 고양이, E : 기타
    private String kind;    //펫 종류
    private String notes;   //특이사항 및 참고사항
    private Long weight;    //펫 몸무게
    private Boolean isNeutralization;   //펫 중성화 여부
    private PetSex sex;     //펫 성별
}
