package com.withpet.backend.dto.user;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

/**
 * 유저 회원가입용 Dto
 */
@Data
public class UserRequestDto {

    @NotBlank(message = "회원 이름을 입력해주세요.")
    private String name;      //유저 이름

    @Email(message = "이메일 양식을 지켜주세요.")
    @NotBlank(message = "이메일을 입력해주세요.")
    private String snsId;     //유저 이메일

    @NotBlank(message = "패스워드를 입력해주세요.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,15}$", message = "영문,숫자,특수문자를 사용하여 8 ~ 15자리의 패스워드를 입력해주세요.")
    private String password;  //유저 비밀번호

    @NotBlank(message = "가입한 SNS 종류를 입력해주세요.")
    private String provider;  //가입한 SNS 종류 : kakao, apple

    @NotBlank(message = "회원 주소를 입력해주세요.")
    private String address;   //회원 주소

    @NotNull(message = "경도를 입력해주세요.")
    private Float x;          //경도

    @NotNull(message = "위도를 입력해주세요.")
    private Float y;          //위도

    @NotNull(message = "자기소개를 입력해주세요.")
    private String introduction;   //유저 자기소개
}
