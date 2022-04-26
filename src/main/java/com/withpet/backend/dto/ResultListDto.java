package com.withpet.backend.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResultListDto {
    int code;
    List data = new ArrayList<>();
    String message;
}
