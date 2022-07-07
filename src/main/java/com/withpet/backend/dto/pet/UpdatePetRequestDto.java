package com.withpet.backend.dto.pet;

import com.withpet.backend.enumc.PetSex;
import com.withpet.backend.enumc.PetType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * 펫 수정 신청용 dto
 */
@Data
public class UpdatePetRequestDto {

    @NotNull(message = "펫 고유번호를 입력해주세요")
    @ApiModelProperty(value = "팻 고유번호", required = true)
    private Long id;
    @NotBlank(message = "펫 이름을 입력해주세요")
    @ApiModelProperty(value = "펫 이름", required = true)
    @Size(min= 1, max = 50, message = "펫 이름은 최대 50자까지입니다.")
    private String name;
    @NotNull(message = "펫 생일을 입력해주세요")
    @ApiModelProperty(value = "펫 생일", required = true)
    private LocalDateTime birth;
    @NotBlank(message = "펫 종류를 입력해주세요")
    @ApiModelProperty(value = "펫 종류", required = true)
    private PetType type;   //펫 타입 D : 강아지, C : 고양이, E : 기타
    @NotBlank(message = "펫 품종을 입력해주세요")
    @ApiModelProperty(value = "펫 품종", required = true)
    @Size(min = 1, max = 50, message = "펫 품종은 최대 50자까지입니다.")
    private String kind;
    @NotNull(message = "펫 특이사항 및 참고사항을 입력해주세요")
    @ApiModelProperty(value = "펫 특이사항 및 참고사항")
    @Size(max = 1000, message = "펫 이름은 최대 1000자까지입니다.")
    private String notes;
    @NotNull(message = "펫 몸무게를 입력해주세요")
    @ApiModelProperty(value = "펫 몸무게", required = true)
    @Size(min = 1, max = 50, message = "펫 이름은 최대 10자까지입니다.")
    private Long weight;
    @NotNull(message = "펫 중성화 여부를 입력해주세요")
    @ApiModelProperty(value = "펫 중성화 여부", required = true)
    private Boolean isNeutralization;
    @NotNull(message = "펫 성별을 입력해주세요")
    @ApiModelProperty(value = "펫 성별", required = true)
    private PetSex sex;
}
