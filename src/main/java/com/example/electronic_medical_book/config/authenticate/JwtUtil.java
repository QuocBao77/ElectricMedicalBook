package com.example.electronic_medical_book.config.authenticate;

import com.example.electronic_medical_book.exception.RequestException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static io.jsonwebtoken.Jwts.builder;

@Component
public class JwtUtil {

    private String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5971";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        Claims claims = null;
        try{
            claims = Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
        }catch (ExpiredJwtException e){
            throw new RequestException((HttpStatus.UNAUTHORIZED.value()) + " - Token expiration");
        }catch (UnsupportedJwtException e){
            throw new RequestException((HttpStatus.UNAUTHORIZED.value()) + " - Token's not supported");
        }catch (MalformedJwtException e){
            throw new RequestException((HttpStatus.UNAUTHORIZED.value()) + " - Invalid format 3 part of token ");
        }catch (SignatureException e){
            throw new RequestException((HttpStatus.UNAUTHORIZED.value()) + " - Invalid format");
        }
        return claims;
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("authorities", userDetails.getAuthorities().
                stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {

        return builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 864_000_000))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token, String username) {
        final String usernameToken = extractUsername(token);
        return (usernameToken.equals(username) && !isTokenExpired(token));
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public UserDetails extractUserDetails(String token) {
        Claims claims = extractAllClaims(token);
        String subject = claims.getSubject();
        List<String> authorities = (List<String>) claims.get("authorities");
        List<SimpleGrantedAuthority> grantedAuthorities = authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return new User(subject, "", grantedAuthorities);
    }
}