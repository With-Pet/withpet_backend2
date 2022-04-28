package com.withpet.backend.dto.user;

import lombok.*;

import javax.validation.constraints.NotBlank;

/**
 * 회원정보 수정 요청용 dto
 */
@Data
public class UpdateUserRequestDto {
    @NotBlank(message = "엑세스 토큰을 입력해 주세요")
    private  String token;  //액세스 토큰
    @NotBlank(message = "회원 이름을 입력해 주세요")
    private  String name;    //회원 이름
    @NotBlank(message = "회원 주소를 입력해 주세요")
    private  String address; //회원 주소
    @NotBlank(message = "회원 소개를 입력해 주세요")
    private  String introduction; //회원 소개
}
