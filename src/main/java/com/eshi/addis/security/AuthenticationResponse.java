package com.eshi.addis.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    private String token;
    private Date expiresIn;
    private String userId;
    private String displayName;
    private boolean firstLogin;
}
