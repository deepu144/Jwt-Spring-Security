package com.deepu.security.springbootjwtrbac.dto;

import lombok.Data;

@Data
public class AuthenticateObject {
    private String email;
    private String password;
}
