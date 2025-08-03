package com.example.E_commerceApplication.security;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {


	private final String SECRET_KEY = "Z2VuZXJhdGVhcmVhbGx5c2VjdXJlc3VwZXJzZWNyZXRrZXk=";
	private final Long EXPIRATION = 1000L * 60 * 60; // 1 hour
	private final SecretKey key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET_KEY));

	// ✅ Generate JWT token with username + roles
	public String generateToken(UserDetails userDetails) {
		List<String> roles = userDetails.getAuthorities().stream().map(authority -> authority.getAuthority())
				.collect(Collectors.toList());

		return Jwts.builder().subject(userDetails.getUsername()).claim("roles", roles).issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + EXPIRATION)).signWith(key).compact();
	}

	// ✅ Extract username from token
	public String extractUsername(String token) {
		return extractAllClaims(token).getSubject();
	}

	// ✅ Validate token (username match + not expired)
	public boolean validateToken(String token, String username) {
		return extractUsername(token).equals(username) && !isTokenExpired(token);
	}

	// ✅ Check if token is expired
	public boolean isTokenExpired(String token) {
		return extractAllClaims(token).getExpiration().before(new Date());
	}

	// ✅ Extract all claims (roles, subject, etc.)
	public Claims extractAllClaims(String token) {
		try {
			JwtParser parser = Jwts.parser().verifyWith(key).build();
			return parser.parseSignedClaims(token).getPayload();
		} catch (Exception e) {
			throw new RuntimeException("Invalid JWT token", e);
		}
	}

	// ✅ Extract roles as authorities
	public List<SimpleGrantedAuthority> extractAuthorities(String token) {
		List<String> roles = extractAllClaims(token).get("roles", List.class);
		return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}
}
