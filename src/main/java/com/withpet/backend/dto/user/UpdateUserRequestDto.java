package com.withpet.backend.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 회원정보 수정 요청용 dto
 */
@Data
public class UpdateUserRequestDto {
    //    @NotBlank(message = "엑세스 토큰을 입력해 주세요")
//    @ApiModelProperty(value = "액세스 토큰", required = true)
//    private  String token;
    private Long id;
    @NotBlank(message = "회원 이름을 입력해 주세요")
    @ApiModelProperty(value = "회원 이름", required = true)
    @Size(max = 100, message = "펫 종류는 최대 100자까지입니다.")
    private String name;
    @NotBlank(message = "회원 주소를 입력해 주세요")
    @ApiModelProperty(value = "회원 주소", required = true)
    @Size(min = 1, max = 100, message = "펫 종류는 최대 100자까지입니다.")
    private String address;
    @NotNull(message = "자개소개를 입력해 주세요")
    @ApiModelProperty(value = "자기소개", required = true)
    @Size(max = 100, message = "자기소개는 최대 200자까지입니다.")
    private String introduction; //회원 소개
}
