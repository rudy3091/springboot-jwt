package com.rudy.jwtloginexample.web.dto;

import com.rudy.jwtloginexample.domain.User.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;

@Getter
@RequiredArgsConstructor
public class UserRegisterResponseDto {
    private String email;
    private String alias;
    private int status;
    private String message;

    @Builder
    public UserRegisterResponseDto(String email, String alias, int status, String message) {
        this.email = email;
        this.alias = alias;
        this.status = status;
        this.message = message;
    }
}
