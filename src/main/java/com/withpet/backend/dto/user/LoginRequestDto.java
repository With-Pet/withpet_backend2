package com.withpet.backend.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 로그인 요청용 dto
 */
@Data
public class LoginRequestDto {

    @NotBlank(message = "회원 아이디를 입력해주세요.")
    @Email(message = "이메일 양식을 지켜주세요.")
    @ApiModelProperty(value = "회원 아이디", required = true)
    @Size(max = 100, message = "회원 아이디는 최대 100자까지입니다.")
    private String snsId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @ApiModelProperty(value = "비밀번호", required = true)
    @Size(max = 50, message = "비밀번호는 최대 50자까지입니다.")
    private String password;
}
