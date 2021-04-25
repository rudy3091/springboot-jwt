package com.rudy.jwtloginexample.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginResponseDto {

    private String alias;
    private String message;
    private String token;

    @Builder
    public LoginResponseDto(String alias, String message, String token) {
        this.alias = alias;
        this.message = message;
        this.token = token;
    }
}
