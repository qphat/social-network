package com.social.backendtweet.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JWTProvider {
    private final SecretKey secretKey;

    public JWTProvider() {
        this.secretKey = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
    }

    // Generate token
    public String generateToken(String email, String authorities) {
        return Jwts.builder()
                .setSubject(email)
                .claim("email", email)
                .claim("authorities", authorities)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JwtConstant.EXPIRATION_TIME))
                .signWith(secretKey)
                .compact();
    }

    // Get email from JWT
    public String getEmailFromToken(String jwt) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(jwt)
                .getBody();
        return claims.get("email", String.class);
    }
}