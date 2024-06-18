package com.deepu.security.springbootjwtrbac.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserObject {
    private Integer id;
    private String email;
    private String password;
    private String role;
}
