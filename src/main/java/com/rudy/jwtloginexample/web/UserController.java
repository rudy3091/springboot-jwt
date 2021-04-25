package com.rudy.jwtloginexample.web;

import com.rudy.jwtloginexample.domain.User.User;
import com.rudy.jwtloginexample.service.User.UserService;
import com.rudy.jwtloginexample.web.dto.UserRegisterRequestDto;
import com.rudy.jwtloginexample.web.dto.UserRegisterResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/api/user")
    public List<User> userList() {
        return this.userService.findAll();
    }

    @PostMapping("/api/user")
    @ResponseStatus(HttpStatus.CREATED)
    public UserRegisterResponseDto register(@RequestBody UserRegisterRequestDto requestDto) {
        User user = this.userService.save(requestDto);
        return UserRegisterResponseDto.builder()
                .email(user.getEmail())
                .alias(user.getAlias())
                .status(201)
                .message("user has been registered")
                .build();
    }
}
