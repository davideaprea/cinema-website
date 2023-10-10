package com.epicode.Spring.security.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.epicode.Spring.security.entity.User;
import com.epicode.Spring.security.exception.MyAPIException;
import com.epicode.Spring.security.repository.UserRepository;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app-jwt-secret}")
    private String jwtSecret;

    @Value("${app-jwt-expiration-milliseconds}")
    private long jwtExpirationDate;
    
    @Autowired private UserRepository userRepo;
    
    public String generateEmailVerificationToken(String email) {
    	Date expirationDate = new Date(System.currentTimeMillis() + 86_400_000);
    	byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
    	
        return Jwts.builder()
            .setSubject(email)
            .setExpiration(expirationDate)
            .signWith(Keys.hmacShaKeyFor(keyBytes))
            .compact();
    }

    // generate JWT token
    public String generateToken(Authentication authentication){
        String username = authentication.getName();
        
        User user=new User();
        if(userRepo.existsByUsername(username))
        	user=userRepo.findByUsername(username).get();
        else user=userRepo.findByEmail(username).get();
        
        Date currentDate = new Date();

        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

        String token = Jwts.builder()
                .setSubject(username)
                .claim("role", user.getRoles())
                .claim("verified", user.isVerified())
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(key())
                .compact();
        return token;
    }

    private Key key(){
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecret)
        );
    }

    // get username from Jwt token
    public String getUsername(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();
        String username = claims.getSubject();
        return username;
    }
    
    public String getEmail(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();
        String email = claims.getSubject();
        return email;
    }

    // validate Jwt token
    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parse(token);
            return true;
        } catch (MalformedJwtException ex) {
            throw new MyAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new MyAPIException(HttpStatus.BAD_REQUEST, "Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            throw new MyAPIException(HttpStatus.BAD_REQUEST, "Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new MyAPIException(HttpStatus.BAD_REQUEST, "JWT claims string is empty.");
        }
    }
}
