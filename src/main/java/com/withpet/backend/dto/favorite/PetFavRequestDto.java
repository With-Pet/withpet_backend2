package com.withpet.backend.dto.favorite;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PetFavRequestDto {
    @NotNull(message = "게시물 고유번호를 입력해주세요")
    @ApiModelProperty(value = "게시물 고유번호", required = true)
    private Long postId;

    @NotNull(message = "회원 고유번호를 입력해주세요")
    @ApiModelProperty(value = "회원 고유번호", required = true)
    private Long userId;
}
