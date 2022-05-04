package com.withpet.backend.dto.pet;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 반려동물 프로필 반환용 dto
 */
@Data
@AllArgsConstructor
public class GetPetResponseDto {
    private String name;
}
