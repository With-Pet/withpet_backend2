package com.withpet.backend.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 본인 자격증 등록 반환용 dto
 */
@Data
@AllArgsConstructor
public class RegisterCerResponseDto {
    private String name;    //자격증 이름
    private String agency;  //취득 기관
    private LocalDateTime date;  //취득 날짜
}
