package com.example.marcosvendas.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JWTUtil {

    @Value("${jwt-secret}")
    private String scret;

    @Value("60000")
    private String expiration;


    public String generateToken(String userName) {


        return Jwts.builder()
                .setSubject(userName)
                /*      .setExpiration(new Date(System.currentTimeMillis() + expiration))*/
                .signWith(SignatureAlgorithm.HS512, scret.getBytes())
                .compact();
    }


}
