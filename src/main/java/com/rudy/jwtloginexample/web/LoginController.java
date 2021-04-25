package com.rudy.jwtloginexample.web;

import com.rudy.jwtloginexample.config.jwt.JwtProvider;
import com.rudy.jwtloginexample.domain.User.User;
import com.rudy.jwtloginexample.service.User.UserService;
import com.rudy.jwtloginexample.web.dto.LoginRequestDto;
import com.rudy.jwtloginexample.web.dto.LoginResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class LoginController {

    private UserService userService;
    private JwtProvider jwtProvider;

    @PostMapping("/api/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto) {
        try {
            User user = this.userService.findByEmail(loginRequestDto.getEmail());
            return LoginResponseDto.builder()
                    .message("Login success")
                    .alias(user.getAlias())
                    .token(this.jwtProvider.createToken(user.getId()))
                    .build();
        } catch (Exception e) {
            return new LoginResponseDto(null, "Login failed", null);
        }
    }
}
