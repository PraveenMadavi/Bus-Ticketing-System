package com.client.busticket.auth_service.components;


import com.client.busticket.auth_service.entity.Users;
import io.jsonwebtoken.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

//@ConfigurationProperties(prefix = "jwt")
/**
 * FUTURE NOTE
 * Remove
 * >> manual parsing methods
 * >> validation methods
 * because they don't require in auth_service.
 */
@Getter
@Setter
@Component
@AllArgsConstructor
public class JwtHelper {

    private final RSAPrivateKey rsaPrivateKey;

    private final RSAPublicKey rsaPublicKey;

    // Token validity (1 hours)
    public static final long JWT_TOKEN_VALIDITY = 60 * 60 * 1000;

    //.......................................................

    // Generate token for any UserDetails implementation
    public String generateToken(Users users) {
        Map<String, Object> claims = new HashMap<>();
        // Add role/authorities if needed
        claims.put("roles", users.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList());
        return doGenerateToken(claims, users.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .addClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(rsaPrivateKey,SignatureAlgorithm.RS256)
                .compact();
    }

    // Validate token against UserDetails
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername()) && ! isTokenExpired(token);
    }

    /**
     * Retrieve username from the JWT token.
     */
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    /**
     * Retrieve expiration date from the JWT token.
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    /**
     * Retrieve a specific claim from the JWT token.
     */
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Retrieve all claims from the JWT token using the secret key.
     */
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(rsaPublicKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Check if the token has expired.
     */
    private boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }


}