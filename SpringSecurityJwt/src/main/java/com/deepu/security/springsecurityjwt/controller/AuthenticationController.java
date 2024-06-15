package com.deepu.security.springsecurityjwt.controller;

import com.deepu.security.springsecurityjwt.request.AuthenticationRequest;
import com.deepu.security.springsecurityjwt.request.RegisterRequest;
import com.deepu.security.springsecurityjwt.response.AuthenticationResponse;
import com.deepu.security.springsecurityjwt.service.AuthenticationService;
import com.deepu.security.springsecurityjwt.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));

    }

}
