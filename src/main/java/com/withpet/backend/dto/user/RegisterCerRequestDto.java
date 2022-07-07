package com.withpet.backend.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * 본인 자격증 등록 요청용 dto
 */
@Data
public class RegisterCerRequestDto {

//    @NotBlank(message = "엑세스 토큰을 입력해 주세요")
//    private  String token;  //액세스 토큰

    @NotNull(message = "회원 고유번호를 입력해주세요")
    @ApiModelProperty(value = "회원 고유번호를", required = true)
    @Size(max = 10, message = "펫 종류는 최대 10자까지입니다.")
    private Long id;        //회원 고유번호

    @NotBlank(message = "자격증 이름을 입력해주세요")
    @ApiModelProperty(value = "자격증 이름", required = true)
    @Size(min = 1, max = 50, message = "자격증 이름은 최대 50자까지입니다.")
    private String name;    //자격증 이름

    @NotBlank(message = "취득 기관 이름을 입력해주세요")
    @ApiModelProperty(value = "취득 기관명", required = true)
    @Size(min = 1, max = 50, message = "취득 기관명은 최대 50자까지입니다.")
    private String agency;  //취득 기관

    @NotNull(message = "취득 날짜를 입력해주세요")
    @ApiModelProperty(value = "취득 날짜", required = true)
    private LocalDateTime date;  //취득 날짜
}
