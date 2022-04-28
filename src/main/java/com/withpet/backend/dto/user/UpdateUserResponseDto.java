package com.withpet.backend.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 회원정보 수정 반환용 dto
 */
@Data
@AllArgsConstructor
public class UpdateUserResponseDto {
    private  String name;    //회원 이름
    private  String address; //회원 주소
    private  String introduction; //회원 소개
}
