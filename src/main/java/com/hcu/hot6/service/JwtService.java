package com.hcu.hot6.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JwtService {

    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long ACCESS_TOKEN_EXPIRY = 3600L; // 1 hour

    public String generateToken(String email) {
        Instant now = Instant.now();

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(email)
                .setIssuedAt(new Date(now.toEpochMilli()))
                .setExpiration(new Date(now.plusSeconds(ACCESS_TOKEN_EXPIRY).toEpochMilli()))
                .signWith(key)
                .compact();
    }

    public Optional<Claims> validate(Optional<String> token) {
        try {
            return Optional.of(token)
                    .filter(Optional::isPresent)
                    .map(
                            jwt ->
                                    Jwts.parserBuilder()
                                            .setSigningKey(key)
                                            .build()
                                            .parseClaimsJws(jwt.get())
                                            .getBody());
        } catch (JwtException e) {
            log.info(e.getMessage());
        }
        return Optional.empty();
    }
}
