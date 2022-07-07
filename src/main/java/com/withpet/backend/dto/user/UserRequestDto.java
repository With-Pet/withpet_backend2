package com.withpet.backend.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

/**
 * 유저 회원가입용 Dto
 */
@Data
public class UserRequestDto {

    @NotBlank(message = "닉네임을 입력해주세요.")
    @ApiModelProperty(value = "닉네임", required = true)
    @Size(min = 1, max = 100, message = "닉네임은 최대 100자까지입니다.")
    private String name;

    @Email(message = "이메일 양식을 지켜주세요.")
    @NotBlank(message = "이메일을 입력해주세요.")
    @ApiModelProperty(value = "회원 아이디", required = true)
    @Size(max = 100, message = "회원 아이디는 최대 100자까지입니다.")
    private String snsId;

    @NotBlank(message = "패스워드를 입력해주세요.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,15}$", message = "영문,숫자,특수문자를 사용하여 8 ~ 15자리의 패스워드를 입력해주세요.")
    @ApiModelProperty(value = "패스워드", required = true)
    @Size(max = 100, message = "패스워드는 최대 100자까지입니다.")
    private String password;

    @NotBlank(message = "가입한 SNS 종류를 입력해주세요.")
    @ApiModelProperty(value = "가입한 SNS 종류", required = true)
    private String provider;  //가입한 SNS 종류 : kakao, apple

    @NotBlank(message = "회원 주소를 입력해주세요.")
    @ApiModelProperty(value = "회원 주소", required = true)
    @Size(min = 1, max = 100, message = "회원 주소는 최대 100자까지입니다.")
    private String address;

    @NotNull(message = "경도를 입력해주세요.")
    @ApiModelProperty(value = "경도", required = true)
    @Size(min = 1, max = 100, message = "경도는 최대 100자까지입니다.")
    private Float x;

    @NotNull(message = "위도를 입력해주세요.")
    @ApiModelProperty(value = "위도", required = true)
    @Size(min = 1, max = 100, message = "위도는 최대 100자까지입니다.")
    private Float y;

    @NotNull(message = "자기소개를 입력해주세요.")
    @ApiModelProperty(value = "자기소개", required = true)
    @Size(max = 200, message = "펫 종류는 최대 200자까지입니다.")
    private String introduction;
}
