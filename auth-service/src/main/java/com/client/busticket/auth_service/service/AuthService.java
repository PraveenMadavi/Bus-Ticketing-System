package com.client.busticket.auth_service.service;

import com.client.busticket.auth_service.components.JwtHelper;
import com.client.busticket.auth_service.entity.Users;
import com.client.busticket.auth_service.enums.Role;
import com.client.busticket.auth_service.records.AuthResponse;
import com.client.busticket.auth_service.records.LoginRequest;
import com.client.busticket.auth_service.records.LoginResponse;
import com.client.busticket.auth_service.records.RegisterRequest;
import com.client.busticket.auth_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
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
            throw new IllegalStateException("User already exists");
        }

        Users user = Users.builder()
                .userName((request.name()))
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.USER)
                .build();


            userRepository.save(user);
//        String token = jwtHelper.generateToken(user);
            return new AuthResponse("User registered successfully");

    }

    public LoginResponse login(LoginRequest request) {

        Authentication authenticate = null;
        try {
            authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.email(),
                            request.password()
                    )
            );
        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }

        Users user = (Users) authenticate.getPrincipal();

        String token = jwtHelper.generateToken(user);

        return new LoginResponse(token, "Login successful");
    }
}