package com.rudy.jwtloginexample.web.dto;

import com.rudy.jwtloginexample.domain.User.Role;
import com.rudy.jwtloginexample.domain.User.User;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserRegisterRequestDto {

    private String email;
    private String password;
    private String alias;

    public User toEntity() {
        return new User(this.email, this.password, this.alias, Role.USER);
    }
}
