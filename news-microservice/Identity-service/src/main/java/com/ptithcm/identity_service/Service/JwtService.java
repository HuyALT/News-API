package com.ptithcm.identity_service.Service;

import java.security.Key;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ptithcm.identity_service.token.UserToken;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	public static final String SECRET = "52d6d98f71cf5dd232a2f1c56454f13b41bf0e4fdb43ef13cdfb9410d330f9b9";
	
	  public void validateToken(final String token) {
	        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
	    }
	  	
	    public String generateToken(UserToken token) {
	        Map<String, Object> claims = new HashMap<>();
	        return createToken(claims, token);
	    }

	    private String createToken(Map<String, Object> claims, UserToken token) {
	        return Jwts.builder()
	                .setClaims(claims)
	                .setSubject(Long.toString(token.getId()))
	                .claim("username", token.getUsername())
	                .claim("role", token.getRole())
	                .setIssuedAt(new Date(System.currentTimeMillis()))
	                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
	                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	    }

	    private Key getSignKey() {
	        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
	        return Keys.hmacShaKeyFor(keyBytes);
	    }
}
