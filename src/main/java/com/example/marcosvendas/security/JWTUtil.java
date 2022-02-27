package com.example.marcosvendas.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    @Value("${jwt.secret}")
    private String scret;

    @Value("${jwt.expiration}")
    private Long expiration;


    public String generateToken(String userName) {


        return Jwts.builder()
                .setSubject(userName)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, scret.getBytes())
                .compact();
    }


    public boolean tokenValido(String tokem) {
        Claims claims = getClaims(tokem);
        if (claims != null) {
            String userName = claims.getSubject();
            Date experiationDate = claims.getExpiration();
            Date now = new Date((System.currentTimeMillis()));
            if (userName != null && experiationDate != null && now.before((experiationDate))) {
                return true;
            }
        }
        return false;
    }

    private Claims getClaims(String tokem) {
        try {
            return Jwts.parser().setSigningKey(scret.getBytes()).parseClaimsJws(tokem).getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public String getUserName(String tokem) {
        Claims claims = getClaims(tokem);
        if (claims != null) {
            return claims.getSubject();

        }
        return null;
    }
}
