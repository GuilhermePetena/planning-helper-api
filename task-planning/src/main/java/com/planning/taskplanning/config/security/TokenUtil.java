package com.planning.taskplanning.config.security;

import com.planning.taskplanning.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class TokenUtil {

    @Value("${planning.jwt.expiration}")
    private String expiration;

    @Value("${planning.jwt.secret}")
    private String secret;

    public boolean isTokenValid(String token) {
        if(Objects.nonNull(token)) {
            Claims claims = getClaim(token);
            if (Objects.nonNull(claims)) {
                String id = claims.getSubject();
                Date expirationDate = claims.getExpiration();
                Date now = new Date(System.currentTimeMillis());
                if (Objects.nonNull(id) && Objects.nonNull(expirationDate) && now.before(expirationDate)) {
                    return true;
                }
            }
        }
        return false;
    }

    public String tokenGenerate(Authentication authentication) {
        User logged = (User) authentication.getPrincipal();
        Date today = new Date();
        Date expiredDate = new Date(today.getTime() +  Long.parseLong(expiration));
        return Jwts.builder()
                .setIssuer("Planning-API")
                .setSubject(logged.getId())
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String getId(String token) {
        Claims claims = getClaim(token);
        return claims.getSubject();

    }

    private Claims getClaim(String token) {
        return Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token)
                .getBody();
    }
}