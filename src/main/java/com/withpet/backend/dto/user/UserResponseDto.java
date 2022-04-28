package com.withpet.backend.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * 회원가입 결과값 반환용 dto
 */
@Data
@AllArgsConstructor
public class UserResponseDto {
    private Long id;    //회원 고유 번호
    private String name;//회원 이름
}
