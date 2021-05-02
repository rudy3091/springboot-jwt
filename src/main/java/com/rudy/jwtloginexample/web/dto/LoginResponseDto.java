package com.rudy.jwtloginexample.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginResponseDto {

    private String alias;
    private String message;
    private String token;
    private String refreshToken;

    @Builder
    public LoginResponseDto(String alias, String message, String token, String refreshToken) {
        this.alias = alias;
        this.message = message;
        this.token = token;
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }
}
