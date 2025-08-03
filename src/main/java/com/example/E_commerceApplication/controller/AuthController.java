package com.example.E_commerceApplication.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.E_commerceApplication.Data.AuthRequest;
import com.example.E_commerceApplication.Data.UserEntity;
import com.example.E_commerceApplication.Dto.AuthResponse;
import com.example.E_commerceApplication.Dto.UserDTO;
import com.example.E_commerceApplication.repository.UserRepository;
import com.example.E_commerceApplication.security.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	// 🔐 Login endpoint
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
		// Authenticate user
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

		// Set authentication context
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Generate JWT token
		UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
		String jwt = jwtUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthResponse(jwt));
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody UserDTO request) {

		if (userRepository.findByUsername(request.getUsername()).isPresent()) {
			return ResponseEntity.badRequest().body("User already exists");
		}

		UserEntity userEntity = new UserEntity();
		userEntity.setUsername(request.getUsername());
		userEntity.setPassword(passwordEncoder.encode(request.getPassword()));
		userEntity.setAuthorities(Set.of("ROLE_USER")); // Java 9+

		userRepository.save(userEntity);

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		String jwt = jwtUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthResponse(jwt));
	}

	@PostMapping("register-admin")
	public ResponseEntity<?> registerAdmin(@RequestBody UserDTO request) {
		if (userRepository.findByUsername(request.getUsername()).isPresent()) {
			return ResponseEntity.badRequest().body("User already exists");
		}

		UserEntity userEntity = new UserEntity();
		userEntity.setUsername(request.getUsername());
		userEntity.setPassword(passwordEncoder.encode(request.getPassword()));
		userEntity.setAuthorities(Set.of("ROLE_ADMIN")); // Java 9+
		userRepository.save(userEntity);

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		String token = jwtUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthResponse(token));
	}
}
