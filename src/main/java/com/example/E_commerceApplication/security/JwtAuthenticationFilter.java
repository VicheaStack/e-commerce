package com.example.E_commerceApplication.security;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String authHeader = request.getHeader("Authorization");

		// 🛡️ Step 1: Check for missing or invalid Authorization header
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}

		// 🛡️ Step 2: Extract the JWT token
		final String jwt = authHeader.substring(7); // Skip "Bearer "

		try {
			// 🛡️ Step 3: Extract username from JWT
			final String username = jwtUtil.extractUsername(jwt);

			// 🛡️ Step 4: Only authenticate if user not already authenticated
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

				// 🛡️ Step 5: Check token validity
				if (!jwtUtil.isTokenExpired(jwt)) {

					// 🛡️ Step 6: Extract user roles/authorities
					List<SimpleGrantedAuthority> authorities = jwtUtil.extractAuthorities(jwt);

					// 🛡️ Step 7: Create auth token and set context
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username,
							null, authorities);

					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

					// 🛡️ Step 8: Set authenticated user into Spring Security context
					SecurityContextHolder.getContext().setAuthentication(authToken);
				}
			}

		} catch (Exception e) {
			System.out.println("Invalid JWT: " + e.getMessage());
			// Optionally log or send error response here
		}

		// 🔄 Always continue the filter chain
		filterChain.doFilter(request, response);
	}
}
