package com.zorvyn.assignment.services;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm; 
import io.jsonwebtoken.security.Keys;

@Component
public class JWTServices {

    private final String SECRET = "donttellothersdonttellothersdonttellothers";

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }
    @Deprecated
    public String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role) 
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) 
                .compact();
    }
    @Deprecated
    public Claims extractClaims(String token) {
    return Jwts.parser()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
}
}