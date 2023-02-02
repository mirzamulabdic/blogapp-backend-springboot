package com.mirza.blogapp.service;

import com.mirza.blogapp.auth.AuthResponse;
import com.mirza.blogapp.auth.LoginRequest;
import com.mirza.blogapp.auth.RegisterRequest;
import com.mirza.blogapp.config.JwtService;
import com.mirza.blogapp.model.Role;
import com.mirza.blogapp.model.User;
import com.mirza.blogapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthResponse register(RegisterRequest registerData) throws Exception {
        if (registerData.getEmail()==null || registerData.getFirstName()==null|| registerData.getLastName()==null|| registerData.getPassword()==null)
        {
            throw new RuntimeException("Please input all fields!!");
        }
        var user= User.builder()
                .firstName(registerData.getFirstName())
                .lastName(registerData.getLastName())
                .email(registerData.getEmail())
                .password(passwordEncoder.encode(registerData.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var token = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse login(LoginRequest loginData) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginData.getEmail(),
                        loginData.getPassword()
                )
        );
        var user = userRepository.findUserByEmail(loginData.getEmail())
                .orElseThrow();
        var token = jwtService.generateToken(user);
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return AuthResponse.builder()
                .token(token)
                .id(user.getId())
                .expiresIn(3600)
                .build();
    }

    public Boolean isUserExist(String email) {
        var user = userRepository.findUserByEmail(email).orElseThrow();

        if(user==null){
            return false;
        } else {
            return true;
        }
    }
}

