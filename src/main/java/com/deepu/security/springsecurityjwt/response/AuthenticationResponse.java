package com.deepu.security.springsecurityjwt.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationResponse {
    public String token;
}
