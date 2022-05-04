package com.withpet.backend.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetUserProfileResponseDto {
    private String name;    //유저 이름
    private String address; //유저 주소
    private String introduction; //유저 소개
}
