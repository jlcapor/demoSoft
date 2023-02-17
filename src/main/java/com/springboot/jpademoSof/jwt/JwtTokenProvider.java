package com.springboot.jpademoSof.jwt;

import com.springboot.jpademoSof.dto.JwtAuthenticationResponse;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwtTokenProvider {

    private static final String AUTHORITIES_KEY = "auth";
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30;
    private final Key key;

    public JwtTokenProvider(@Value("${app.jwtSecret}") String jwtSecret) {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    public JwtAuthenticationResponse generateToken(Authentication authentication) {

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        String authorities = userPrincipal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date tokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);

        String jwtToken = Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .claim(AUTHORITIES_KEY, userPrincipal.getAuthorities())
                .setExpiration(tokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
        return new JwtAuthenticationResponse(jwtToken,tokenExpiresIn.getTime() , userPrincipal.getId(), userPrincipal.getEmail(), Collections.singletonList(authorities));

    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty.");
        }

        return false;
    }

    public Long getUserIdFromJWT(String accessToken) {
        Claims claims;
        try {
            claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return Long.parseLong(claims.getSubject());
    }

}
