package com.example.spamclassifier.util;

import com.example.spamclassifier.api.request.LogInRequest;
import com.example.spamclassifier.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationUtil {

    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationUtil(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public Authentication getAuthentication(LogInRequest request) {
        try {
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (DisabledException e) {
            throw new CustomException("User Inactive", HttpStatus.FORBIDDEN.value());
        } catch (BadCredentialsException e) {
            throw new CustomException("Invalid Credentials", HttpStatus.BAD_REQUEST.value());
        }
    }
}
