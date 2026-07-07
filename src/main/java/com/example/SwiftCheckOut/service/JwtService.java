package com.example.SwiftCheckOut.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.SwiftCheckOut.entity.Customer;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    String secret;

    @Value("${jwt.exp}")
    Long exp;

    public String generateToken(Customer user) {
        Map<String,String> c = new HashMap<>();
        c.put("role", user.getRole().name());
        return Jwts.builder()
               .subject(user.getEmail())
               .claim("username", user.getUsername())
               .claims(c)
               .issuedAt(new Date(System.currentTimeMillis()))
               .expiration(new Date(System.currentTimeMillis() + exp))
               .signWith(getSecretKey())
               .compact();
    }

    SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public Claims extractAllClaims(String token){
        return Jwts.parser()
               .verifyWith(getSecretKey())
               .build()
               .parseSignedClaims(token)
               .getPayload();
    }

    public <T> T extractClaim(String token,Function<Claims,T> resolver){
        return resolver.apply(extractAllClaims(token));
    }

    public String extractUsername(String token) {
       return extractClaim(token, Claims::getSubject);
    }

    public boolean isTokenValidate(String token){
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    public boolean validateToken(String token,UserDetails user){
        String userdata = extractUsername(token);
        return userdata.equals(user.getUsername()) && !isTokenValidate(token);

    }

}
