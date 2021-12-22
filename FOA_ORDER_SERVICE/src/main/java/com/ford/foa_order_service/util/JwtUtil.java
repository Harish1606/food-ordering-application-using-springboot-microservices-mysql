package com.ford.foa_order_service.util;

import com.ford.foa_order_service.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.function.Function;

@Service
public class JwtUtil {

    @Autowired
    @Lazy
    private RestTemplate restTemplate;

    private final String secret = "SECRET_KEY";

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public Boolean isTokenExpired(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            return extractExpiration(token).before(new Date());
        }
        return true;
    }

    public User verifyToken(String token) {
        String verify_token_url = "http://AUTHENTICATION-SERVICE/authentication/verifyToken";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/json");
        headers.add("Authorization", token);
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<User> response = restTemplate.exchange(verify_token_url, HttpMethod.GET, request, User.class);
        User user = response.getBody();
        return user;
    }
}
