package com.deepu.security.springbootjwtrbac.service;

import com.deepu.security.springbootjwtrbac.repository.OurUserRepo;
import com.deepu.security.springbootjwtrbac.repository.TokenRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
public class LogoutService implements LogoutHandler {
    @Autowired
    private TokenRepo tokenRepo;
    @Autowired
    private OurUserRepo ourUserRepo;
    @Autowired
    private JWTUtils jwtUtils;
    @Transactional
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) return;
        var jwtToken = authHeader.substring(7);
        var userEmail = jwtUtils.extractUsername(jwtToken);
        System.out.println(userEmail+" ......");
        tokenRepo.deleteTokensByOurUsers(ourUserRepo.findByEmail(userEmail).get());
        SecurityContextHolder.clearContext();
    }
}
