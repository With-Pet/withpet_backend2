package com.withpet.backend.dto.user;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * 로그인 요청용 dto
 */
@Data
public class LoginRequestDto {

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 양식을 지켜주세요.")
    private String snsId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
}
