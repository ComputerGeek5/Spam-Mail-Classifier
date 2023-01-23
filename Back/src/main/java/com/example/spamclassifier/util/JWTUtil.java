package com.example.spamclassifier.util;

import com.example.spamclassifier.dto.UserDTO;
import com.example.spamclassifier.exception.CustomException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
public class JWTUtil {

    private final Environment environment;

    public JWTUtil(Environment environment) {
        this.environment = environment;
    }

    public String getusername(final String token) {
        try {
            Claims body = Jwts.parser().parseClaimsJwt(token).getBody();

            return body.getSubject();
        } catch (Exception e) {
            System.out.println(e.getMessage() + " => " + e);
        }

        return null;
    }

    public String generateToken(UserDTO user) {
        Claims claims = Jwts.claims().setSubject(user.getUsername());

        final long nowMillis = System.currentTimeMillis();
        final long tokenValidity = Long.parseLong(Objects.requireNonNull(environment.getProperty("app.jwt_token_validity")));
        final long expMillis = nowMillis + tokenValidity;

        Date exp = new Date(expMillis);

        return Jwts.builder().setClaims(claims).setIssuedAt(new Date(nowMillis)).setExpiration(exp).compact();
    }

    public void validateToken(final String token) {
        try {
            Jwts.parser().parseClaimsJwt(token);
        } catch (SignatureException ex) {
            throw new CustomException("Invalid JWT signature", HttpStatus.BAD_REQUEST.value());
        } catch (MalformedJwtException ex) {
            throw new CustomException("Invalid JWT token", HttpStatus.BAD_REQUEST.value());
        } catch (ExpiredJwtException ex) {
            throw new CustomException("Expired JWT token", HttpStatus.BAD_REQUEST.value());
        } catch (UnsupportedJwtException ex) {
            throw new CustomException("Unsupported JWT token", HttpStatus.BAD_REQUEST.value());
        } catch (IllegalArgumentException ex) {
            throw new CustomException("JWT claims string is empty.", HttpStatus.BAD_REQUEST.value());
        }
    }
}
