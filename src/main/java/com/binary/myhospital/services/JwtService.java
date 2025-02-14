package com.binary.myhospital.services;

import com.binary.myhospital.configs.JwtConfigProperty;
import com.binary.myhospital.entities.UserCredential;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    @Autowired
    private JwtConfigProperty jwtConfigProperty;
    @Autowired
    private UserDetailsService userDetailsService;

    public JwtService(){}

    public JwtService(JwtConfigProperty jwtConfigProperty, UserDetailsService userDetailsService) {
        this.jwtConfigProperty = jwtConfigProperty;
        this.userDetailsService = userDetailsService;
    }
    public String generateToken(String username){
        UserCredential userCredential = (UserCredential) userDetailsService.loadUserByUsername(username);
        Map<String,Object> claims = new HashMap<>();
        claims.put("sub", userCredential.getUsername());
        claims.put("role", userCredential.getRole());
        return generateToken(claims, userCredential);
    }
    private String generateToken(Map<String,Object> claims, UserCredential userCredential){
        return Jwts.builder()
                .claims(claims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+jwtConfigProperty.getExpiration()))
                .signWith(getSecretKey())
                .compact();
    }
    private SecretKey getSecretKey(){
        byte[] keyBytes = Decoders.BASE64.decode(jwtConfigProperty.getSecret());
        return Keys.hmacShaKeyFor(keyBytes);
    }
    //    this is the JWT parsing for verification
    public <T> T extractClaims(String token, Function<Claims,T> claimsResolver ){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractUsername(String token){
        return extractClaims(token, Claims::getSubject);
    }

    public Date extractExpiration(String token){
        return extractClaims(token, Claims::getExpiration);
    }

    public boolean isTokenValid(String token, UserCredential userCredential){
        final String username=extractUsername(token);
        return (username.equalsIgnoreCase(userCredential.getUsername())
                && !isTokenExpired(token));
    }
    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }
}
