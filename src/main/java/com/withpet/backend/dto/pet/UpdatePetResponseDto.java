package com.withpet.backend.dto.pet;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *  펫 수정 반환용 dto
 */
@Data
@AllArgsConstructor
public class UpdatePetResponseDto {
    private String name;
}
