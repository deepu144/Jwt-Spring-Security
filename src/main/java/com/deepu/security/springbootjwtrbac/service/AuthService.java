package com.deepu.security.springbootjwtrbac.service;

import com.deepu.security.springbootjwtrbac.dto.AuthenticateObject;
import com.deepu.security.springbootjwtrbac.dto.ResponseObject;
import com.deepu.security.springbootjwtrbac.dto.UserObject;
import com.deepu.security.springbootjwtrbac.entity.OurUsers;
import com.deepu.security.springbootjwtrbac.repository.OurUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashMap;

@Service
public class AuthService {
    @Autowired
    private OurUserRepo ourUserRepo;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    public ResponseObject signUp(UserObject userObject){
        ResponseObject response = new ResponseObject();
        OurUsers ourUsers = new OurUsers();
        ourUsers.setEmail(userObject.getEmail());
        ourUsers.setPassword(passwordEncoder.encode(userObject.getPassword()));
        ourUsers.setRole(userObject.getRole());
        ourUserRepo.save(ourUsers);
        userObject.setId(ourUsers.getId());
        String jwt = jwtUtils.generateToken(ourUsers);
        String refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), ourUsers);
        response.setOurUsers(ourUsers);
        response.setToken(jwt);
        response.setRefreshToken(refreshToken);

        return response;
    }

    public ResponseObject signIn(AuthenticateObject authenticateObject){
        ResponseObject response = new ResponseObject();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticateObject.getEmail(),authenticateObject.getPassword()));
        OurUsers user = ourUserRepo.findByEmail(authenticateObject.getEmail()).orElseThrow();
        System.out.println("USER IS: "+ user);
        var jwt = jwtUtils.generateToken(user);
        var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
        response.setToken(jwt);
        response.setRefreshToken(refreshToken);
        response.setOurUsers(user);
        return response;
    }

    public ResponseObject refreshToken(ResponseObject responseObject){
        ResponseObject response = new ResponseObject();
        String ourEmail = jwtUtils.extractUsername(responseObject.getToken());
        OurUsers users = ourUserRepo.findByEmail(ourEmail).orElseThrow();
        if (jwtUtils.isTokenValid(responseObject.getToken(), users)) {
            var jwt = jwtUtils.generateToken(users);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), users);
            response.setOurUsers(users);
            response.setToken(jwt);
            response.setRefreshToken(refreshToken);
        }
        return response;
    }
}
