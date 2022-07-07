package com.withpet.backend.dto.post;

import com.withpet.backend.enumc.PetType;
import com.withpet.backend.enumc.PostType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class GetPostResponseDto {
    private Long id;
    private Long price;
    private String address;
    private PostType posttype;
    private PetType petType;
    private Boolean isFavorite;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
