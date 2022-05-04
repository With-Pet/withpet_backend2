package com.withpet.backend.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * 회원가입 결과값 반환용 dto
 */
@Data
@AllArgsConstructor
public class UserResponseDto {
    private Long id;    //회원 고유 번호
    private String name;//회원 이름
    private String snsId;   //유저 이메일
    private Float x;    //경도
    private Float y;    //위도
    private LocalDateTime createdAt; //유저 생성 일자
}
