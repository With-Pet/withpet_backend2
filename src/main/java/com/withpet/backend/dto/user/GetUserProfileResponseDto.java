package com.withpet.backend.dto.user;

import com.withpet.backend.domain.Service;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class GetUserProfileResponseDto {
    private String name;            //유저 이름
    private String address;         //유저 주소
    private String introduction;    //유저 소개
    private List<Service> services; //유저 이용가능 서비스
}
