package com.mirza.blogapp.controller;

import com.mirza.blogapp.auth.AuthResponse;
import com.mirza.blogapp.auth.LoginRequest;
import com.mirza.blogapp.auth.RegisterRequest;
import com.mirza.blogapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserAuthController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerData) throws Exception {

//        Boolean userExist=userService.isUserExist(registerData.getEmail());
//        if (userExist==false){
//            throw new RuntimeException("User already exists!");
//        }

        return ResponseEntity.ok(userService.register(registerData));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginData){
        return ResponseEntity.ok(userService.login(loginData));
    }
}
