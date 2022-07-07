package com.withpet.backend.dto.post;

import com.withpet.backend.enumc.PostType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class RegisterPostRequestDto {
    @NotNull(message = "회원 고유번호는 필수 값입니다.")
    @ApiModelProperty(value = "회원 고유번호", required = true)
    private Long id;

    @NotBlank(message = "제목은 필수 값입니다.")
    @Size(min = 2, max = 100, message = "제목은 최소 2자에서 100자까지입니다.")
    @ApiModelProperty(value = "제목", required = true)
    private String title;

    @NotNull(message = "게시물 타입은 필수 값입니다.")
    @ApiModelProperty(value = "게시물 타입", required = true)
    private PostType type;   // 산책 : W, 돌봄 : C, 체험 : E

    @NotNull(message = "반려동물 고유 아이디는 필수 값입니다.")
    @ApiModelProperty(value = "반려동물 고유 아이디", required = true)
    private Long petId;

    @NotNull(message = "시간당 이용 금액은 필수 값입니다.")
    @Size(max = 100, message = "시간당 이용 금액은 최대 100자까지입니다.")
    @ApiModelProperty(value = "시간당 이용 금액", required = true)
    private Long price;

    @NotNull(message = "경도는 필수 값입니다.")
    @ApiModelProperty(value = "경도", required = true)
    private Float x;

    @NotNull(message = "위도는 필수 값입니다.")
    @ApiModelProperty(value = "위도", required = true)
    private Float y;

    @NotEmpty(message = "지역명은 필수 값입니다.")
    @Size(max = 100, message = "지역명은 최대 100자까지입니다.")
    @ApiModelProperty(value = "게시물 지역명", required = true)
    private String address;

    @NotEmpty
    @Size(max = 1000, message = "특이사항은 최대 1000자까지입니다.")
    @ApiModelProperty(value = "특이사항", required = true)
    private String specifics;

    @NotEmpty
    @Size(max = 1000, message = "요청사항은 최대 1000자까지입니다.")
    @ApiModelProperty(value = "요청사항", required = true)
    private String description;

//    @ApiModelProperty(value = "게시물 사진", required = true)
//    private List<MultipartFile> files;

    @NotNull(message = "시작일은 필수 값입니다.")
    @ApiModelProperty(value = "시작일", required = true)
    private LocalDateTime startDate;

    @NotNull(message = "종료일은 필수 값입니다.")
    @ApiModelProperty(value = "종료일", required = true)
    private LocalDateTime endDate;
}
