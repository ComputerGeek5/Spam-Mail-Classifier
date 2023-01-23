package com.example.spamclassifier.service.impl;

import com.example.spamclassifier.api.request.LogInRequest;
import com.example.spamclassifier.exception.CustomException;
import com.example.spamclassifier.service.abst.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication authenticate(LogInRequest request) {
        try {
            Authentication authentication =  authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            return authentication;
        } catch (DisabledException e) {
            throw new CustomException("User Inactive !", HttpStatus.FORBIDDEN.value());
        } catch (BadCredentialsException e) {
            throw new CustomException("Invalid Credentials !", HttpStatus.FORBIDDEN.value());
        } catch (InternalAuthenticationServiceException e) {
            throw new CustomException("Username not found !", HttpStatus.BAD_REQUEST.value());
        }
    }
}
