package com.rudy.jwtloginexample.web;

import com.rudy.jwtloginexample.config.jwt.JwtProvider;
import com.rudy.jwtloginexample.domain.User.User;
import com.rudy.jwtloginexample.service.User.UserService;
import com.rudy.jwtloginexample.web.dto.LoginRequestDto;
import com.rudy.jwtloginexample.web.dto.LoginResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
public class LoginController {

    private UserService userService;
    private JwtProvider jwtProvider;

    @PostMapping("/api/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletRequest req) {
        try {
            Cookie[] cookies = req.getCookies();
            if (cookies != null) {
                List<Cookie> cookieList = Arrays.stream(cookies)
                        .filter(c -> c.getName().equals("refresh_token"))
                        .collect(Collectors.toList());
                System.out.println("it has refresh token!");
            }

            User user = this.userService.findByEmail(loginRequestDto.getEmail());
            User userp = this.userService.findByPassword(loginRequestDto.getPassword());
            if (user != userp) throw new Exception("user not found");

            LoginResponseDto responseDto = LoginResponseDto.builder()
                    .message("Login success")
                    .alias(user.getAlias())
                    .token(this.jwtProvider.createToken(user.getId()))
                    .refreshToken(this.jwtProvider.createRefreshToken(user.getId(), user.getAlias()))
                    .build();

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Set-Cookie", "access_token=" + responseDto.getToken() + "; Path=/; Max-Age=60");
            httpHeaders.add("Set-Cookie", "refresh_token=" + responseDto.getRefreshToken() + "; Path=/; Max-Age=120; httpOnly");

            return new ResponseEntity<>(responseDto, httpHeaders, HttpStatus.OK);
        } catch (Exception e) {
            LoginResponseDto responseDto = new LoginResponseDto(null, "Login failed", null, null);
            return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
        }
    }
}
