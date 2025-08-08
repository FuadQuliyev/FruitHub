package com.example.fruithub.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;


@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    private Key getSigninKey(){
        byte[] key = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(key);
    }

    public String generateToken(String email){
        return Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*30))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private<T> T exportToken(String token, Function<Claims, T> claimFunction){
        final Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claimFunction.apply(claims);
    }

    public String findEmail(String token){
        return exportToken(token, Claims::getSubject);
    }


    public boolean tokenControl(String token, UserDetails userdetails){
        final String email = findEmail(token);
        return (email.equals(userdetails.getUsername()) && !exportToken(token, Claims::getExpiration).before(new Date()));
    }



}
