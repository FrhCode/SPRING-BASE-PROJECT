package com.farhan.baseproject.service;

import java.util.Date;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.farhan.baseproject.model.Role;
import com.farhan.baseproject.model.User;
import com.farhan.baseproject.repository.RoleRepository;
import com.farhan.baseproject.repository.UserRepository;
import com.farhan.baseproject.request.LoginRequest;
import com.farhan.baseproject.request.RegisterRequest;
import com.farhan.baseproject.response.AuthenticationResponse;

import io.jsonwebtoken.Claims;
import lombok.*;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	private final RoleRepository roleRepository;

	public AuthenticationResponse register(RegisterRequest request) {
		User user = new User();
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setPhoneNumber(null);
		user.setName(request.getUsername());

		Role role = roleRepository.findByName("User").get();
		user.addRole(role);

		String jwtToken = jwtService.generateToken(user);
		Date expDate = jwtService.extractClaim(jwtToken, Claims::getExpiration);
		return new AuthenticationResponse(user, jwtToken, expDate);
	}

	public AuthenticationResponse authenticate(LoginRequest request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getEmail(),
						request.getPassword()));
		User user = userRepository.findByEmail(request.getEmail()).get();

		String jwtToken = jwtService.generateToken(user);

		System.out.println(new Date(System.currentTimeMillis()));
		Date expDate = jwtService.extractClaim(jwtToken, Claims::getExpiration);
		return new AuthenticationResponse(user, jwtToken, expDate);
	}

}
