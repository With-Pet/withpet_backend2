package com.withpet.backend.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterPostResponseDto {
    private Long id;    //게시물 고유 번호
    private String title; //게시물 제목
}
