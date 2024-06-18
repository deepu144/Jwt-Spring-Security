package com.deepu.security.springbootjwtrbac.dto;

import com.deepu.security.springbootjwtrbac.entity.OurUsers;
import lombok.Data;

@Data
public class ResponseObject {
    private String token;
    private String refreshToken;
    private OurUsers ourUsers;
}
