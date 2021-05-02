package com.rudy.jwtloginexample.config.jwt;

import com.rudy.jwtloginexample.service.User.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtProvider {

    private String secret = "secret"; // secret key
    private final Long lifetime = 30 * 60 * 1000L; // 30 minutes

    private final UserService userService;

    @PostConstruct
    protected void init() {
        this.secret = Base64.getEncoder().encodeToString(this.secret.getBytes());
    }

    public String createToken(Long userPk) {
        Claims claims = Jwts.claims().setSubject(userPk.toString());
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + this.lifetime))
                .signWith(SignatureAlgorithm.HS512, this.secret)
                .compact();
    }

    public String createRefreshToken(Long userPk, String userAlias) {
        Claims claims = Jwts.claims().setSubject(userPk.toString());
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .claim("alias", userAlias)
                .setExpiration(new Date(now.getTime() + this.lifetime))
                .signWith(SignatureAlgorithm.HS512, this.secret)
                .compact();
    }

    public Long getUserPk(String token) {
        return Long.parseLong((String) Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody().get("sub"));
    }

    public Authentication getAuthentication(String token) {
        UserDetails user = this.userService.findById(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(user, "", user.getAuthorities());
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("x-access-token");
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(this.secret)
                    .parseClaimsJws(token);
            return claimsJws.getBody().getExpiration().after(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
