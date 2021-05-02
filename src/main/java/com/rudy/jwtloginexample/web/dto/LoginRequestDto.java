package com.rudy.jwtloginexample.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;

@Getter
@RequiredArgsConstructor
public class LoginRequestDto {

    private String email;
    private String password;
}
