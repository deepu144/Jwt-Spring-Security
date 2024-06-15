package com.deepu.security.springsecurityjwt.service;

import com.deepu.security.springsecurityjwt.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY = "======================BezKoder=Spring===========================";
    public String extractUserName(String token){
        return extractClaim(token,Claims::getSubject);
    }

    public <T> T extractClaim(String token , Function<Claims,T> claimsResolver){
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(User user){
//        System.out.println("In generate Token : "+user.getEmail() + "........");
        return generateToken(new HashMap<>(),user);
    }

    public String generateToken(Map<String,Object> extraClaims , User user){
//        System.out.println("Token Generate Function : "+ user.getEmail()+".............");
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration((new Date(System.currentTimeMillis() + 1000 * 60 *24)))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean isTokenValid(String token , UserDetails userDetails){
        String userName = extractUserName(token);
//        System.out.println("isTokenValid : " + userName + ".........");
//        System.out.println("isTokenValid2 : " + userDetails.getUsername() + ".........");
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

}
