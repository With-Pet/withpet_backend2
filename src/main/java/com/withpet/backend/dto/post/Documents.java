package com.withpet.backend.dto.post;

import lombok.Data;

@Data
public class Documents {
    private String address_name;    //주소명
    private String address_type;    //주소 타입 (도로명, 지번)
    private String x;               //x 좌표
    private String y;               //y 좌표
}