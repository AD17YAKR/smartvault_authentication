package com.smartvault.authentication.auth.utils;

import com.smartvault.authentication.auth.dto.UserDTO;
import com.smartvault.authentication.auth.exception.AuthException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expirationMs}")
    private long jwtExpirationMs;

    private Key jwtKey;

    @PostConstruct
    public void init() {
        byte[] keyBytes = jwtSecret.getBytes(StandardCharsets.UTF_8);
        this.jwtKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateJwtToken(UserDTO user) {
        return Jwts.builder().subject(user.getUsername())
                .claim("role", user.getRole().toString()).issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(jwtKey)
                .compact();
    }

    public void validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtKey)
                    .build()
                    .parseClaimsJws(token);
        } catch (Exception e) {
            throw new AuthException("Invalid JWT token: " + e.getMessage());
        }
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String getRoleFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role", String.class);
    }

}