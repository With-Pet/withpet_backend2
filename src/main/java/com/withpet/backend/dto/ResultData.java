package com.withpet.backend.dto;

import lombok.Data;

@Data
public class ResultData {

    int code;
    Object data = new Object();
    String message;
}
