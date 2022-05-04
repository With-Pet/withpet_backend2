package com.withpet.backend.dto.pet;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 펫 등록 반환용 dto
 */
@Data
@AllArgsConstructor
public class RegisterPetResponseDto {
    private String name;
}
