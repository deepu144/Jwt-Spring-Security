package com.deepu.security.springsecurityjwt.controller;

import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hello User");
    }

    @GetMapping("/admin")
    @RolesAllowed("ADMIN")
    public ResponseEntity<String> admin(){
        return ResponseEntity.ok("Hello Admin");
    }

}
