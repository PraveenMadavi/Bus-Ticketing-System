package com.client.busticket.auth_service.service;

import com.client.busticket.auth_service.components.JwtHelper;
import com.client.busticket.auth_service.entity.Users;
import com.client.busticket.auth_service.records.AuthResponse;
import com.client.busticket.auth_service.records.LoginRequest;
import com.client.busticket.auth_service.records.LoginResponse;
import com.client.busticket.auth_service.records.RegisterRequest;
import com.client.busticket.auth_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtHelper jwtHelper;

    public AuthResponse register(RegisterRequest request) {

        // check if user exists
        if (userRepository.existsByEmail(request.email())) {
            throw new RuntimeException("User already exists");
        }

        Users user = Users.builder()
                .userName((request.name()))
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(request.role())
                .build();

        try {
            userRepository.save(user);
//        String token = jwtHelper.generateToken(user);
            return new AuthResponse("User registered successfully");
        } catch (Exception e) {
            throw new RuntimeException("ERROR : DURING SAVE USER : " + e);
        }
    }

    public LoginResponse login(LoginRequest request) {

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        Users user = (Users) authenticate.getPrincipal();
//        Users user = userRepository.findByEmail(request.email())
//                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtHelper.generateToken(user);

        return new LoginResponse(token, "Login successful");
    }
}