package com.withpet.backend.dto.user;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 로그인 후 응답값 반환용 dto
 */
@Data
public class AuthResponseDto {

    private String accessToken;
    private String refreshToken;

    private String tokenType = "Bearer";

    public AuthResponseDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

}