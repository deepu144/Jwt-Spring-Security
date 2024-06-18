package com.deepu.security.springbootjwtrbac.controller;

import com.deepu.security.springbootjwtrbac.dto.AuthenticateObject;
import com.deepu.security.springbootjwtrbac.dto.ResponseObject;
import com.deepu.security.springbootjwtrbac.dto.UserObject;
import com.deepu.security.springbootjwtrbac.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseObject> signUp(@RequestBody UserObject userObject){
        return ResponseEntity.ok(authService.signUp(userObject));
    }
    @PostMapping("/signin")
    public ResponseEntity<ResponseObject> signIn(@RequestBody AuthenticateObject signInRequest){
        return ResponseEntity.ok(authService.signIn(signInRequest));
    }
    @PostMapping("/refresh")
    public ResponseEntity<ResponseObject> refreshToken(@RequestBody ResponseObject refreshTokenRequest){
        return ResponseEntity.ok(authService.refreshToken(refreshTokenRequest));
    }
}
