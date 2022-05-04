package com.withpet.backend.dto.pet;

import com.withpet.backend.enumc.PetSex;
import com.withpet.backend.enumc.PetType;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 펫 수정 신청용 dto
 */
@Data
public class UpdatePetRequestDto {

    @NotNull(message = "펫 고유번호를 입력해주세요")
    private Long id;
    @NotBlank(message = "펫 이름을 입력해주세요")
    private String name;
    @NotNull(message = "펫 생일을 입력해주세요")
    private LocalDateTime birth;
    @NotBlank(message = "펫 타입을 입력해주세요")
    private PetType type;   //펫 타입 D : 강아지, C : 고양이, E : 기타
    @NotBlank(message = "펫 종류를 입력해주세요")
    private String kind;
    @NotBlank(message = "펫 특이사항 및 참고사항을 입력해주세요")
    private String notes;
    @NotNull(message = "펫 몸무게를 입력해주세요")
    private Long weight;
    @NotNull(message = "펫 중성화 여부를 입력해주세요")
    private Boolean isNeutralization;
    @NotBlank(message = "펫 성병을 입력해주세요")
    private PetSex sex;
}
