package com.eranga.supermarket.auth_server.service.impl;

import com.eranga.supermarket.auth_server.model.dto.AppUserDto;
import com.eranga.supermarket.auth_server.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${spring.application.security.jwt.token-expiration.access}")
    private Long accessTokenExpiration;
    @Value("${spring.application.security.jwt.secret-key}")
    private String secretKey;

    public String generateAccessToken(AppUserDto appUserDto) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", appUserDto.getId());
        return buildToken(claims,appUserDto.getUserName(),this.accessTokenExpiration);
    }

    public String extractUserName(String jwtToken){
        return extractClaim(jwtToken,Claims::getSubject);
    }

    public Boolean isJwtTokenValid(String jwtToken, UserDetails userDetails){
        String userName = extractUserName(jwtToken);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken));
    }

    private String buildToken(Map<String, Object> claims, String username, long expiration) {
        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .and()
                .signWith(getSignInKey())
                .compact();
    }

    private SecretKey getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(this.secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Date extractExpiration(String jwtToken){
        return extractClaim(jwtToken,Claims::getExpiration);
    }

    private Boolean isTokenExpired(String jwtToken){
        return extractExpiration(jwtToken).before(new Date());
    }

    private  <T> T extractClaim(String jwtToken, Function<Claims, T> claimResolver){
        Claims claims = extractAllClaims(jwtToken);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String jwtToken) {
        return Jwts
                .parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
    }
}
