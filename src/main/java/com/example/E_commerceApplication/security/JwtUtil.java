package com.example.E_commerceApplication.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	// 🔐 Symmetric secret key for signing the JWT
	private final String SECRET_KEY = "your-256-bit-secret-your-256-bit-secret";

	/**
	 * Generate a signing key using the secret.
	 */
	public SecretKey getSigningKey() {
		byte[] keyBytes = SECRET_KEY.getBytes(); // You can add UTF-8 charset for safety
		return Keys.hmacShaKeyFor(keyBytes);
	}

	/**
	 * Generate JWT Token for a given user.
	 * 
	 * @param userDetails authenticated user
	 * @return signed JWT token string
	 */
	public String generateToken(UserDetails userDetails) {
		return Jwts.builder().setSubject(userDetails.getUsername()) // 👤 username as subject
				.setIssuedAt(new Date()) // 🕒 token issue time
				.setExpiration(new Date(System.currentTimeMillis() + 86400000)) // ⏳ 1 day
				.signWith(getSigningKey(), SignatureAlgorithm.HS256) // 🔐 sign the token
				.compact();
	}

	/**
	 * Parse JWT and get all claims.
	 * 
	 * @param token JWT token
	 * @return Claims object
	 */
	private Claims getClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
	}

	/**
	 * Extract username (subject) from the token.
	 * 
	 * @param token JWT token
	 * @return username
	 */
	public String extractUsername(String token) {
		return getClaims(token).getSubject();
	}

	/**
	 * Check if the token is expired.
	 * 
	 * @param token JWT token
	 * @return true if expired
	 */
	private boolean isTokenExpired(String token) {
		return getClaims(token).getExpiration().before(new Date());
	}

	/**
	 * Validate token: correct username and not expired.
	 * 
	 * @param token       JWT token
	 * @param userDetails user from Spring Security
	 * @return true if valid
	 */
	public boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}
