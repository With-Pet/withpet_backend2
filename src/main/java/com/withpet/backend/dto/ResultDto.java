package com.withpet.backend.dto;

import lombok.Data;

import java.util.HashMap;

@Data
public class ResultDto {
    int code;
    HashMap<String,Object> data = new HashMap<String,Object>();
    String message;
}
