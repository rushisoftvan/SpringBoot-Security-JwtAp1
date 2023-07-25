package com.learn1.jwt1.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
@EnableMethodSecurity(prePostEnabled = true)
public class JwtUtil {

    public static final String ISSUER = "sachin";
    public static final String ID = "123";
    @Value("${app.jwt-secret-key}")
    private String jwtSecretKey;

    //1. generate token
    public String generateToken(String subject) {

        System.out.println("subject = " + subject);
        String token = Jwts.builder()
                .setId(ID)
                .setSubject(subject)
                .setIssuer(ISSUER)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(1)))
                .signWith(SignatureAlgorithm.HS256, jwtSecretKey)
                .compact();
        log.info("Token {}", token);

        return token;
    }

    //Read Claims(read token/parser token)
    public Claims getClaims(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecretKey)//jwtSecretKey.getBytes()
                .parseClaimsJws(token)
                .getBody();
        log.info("username {}", claims.getSubject());
        log.info("id {}", claims.getId());
        log.info("expireDate {}", claims.getExpiration());
        return claims;
    }

    //read exp date
    public Date getExpDate(String token) {
        return getClaims(token).getExpiration();
    }

    //read user name
    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    // Validate exp date
    public boolean isTokenExp(String token) {
        Date expDate = getExpDate(token);
        log.info("expDate {}", expDate);
        return expDate.before(new Date(System.currentTimeMillis()));
    }

    //Validate username in token and database,expDate
    public boolean validateToken(String token, String username) {
        String tokenUsername = getUsername(token);
        return (tokenUsername.equals(username) && !isTokenExp(token));
    }
}
