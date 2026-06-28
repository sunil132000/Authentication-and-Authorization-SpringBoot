package com.example.security.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JWTUtil {

    private final long EXPIRATION_TIME = 1000*60*60 ;// 1hour
    private final String SECRET = "my-super-secret-key-that-is-long-enough-1234567890#@#";
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());
    public String generateToken(String userName){
       return Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+ EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

    }

    public String extractUserName(String token){
       Claims body = exractClaims(token);

       return body.getSubject();
    }

    private Claims exractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String username , UserDetails userDetails , String token){
       // TODO - check if username is same as username in userDetails
           return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
        // TODO - check if token is not expired
    }

    public boolean isTokenExpired(String token){
        return exractClaims(token).getExpiration().before(new Date());
    }
}
