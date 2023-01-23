package com.example.spamclassifier.service.abst;

import com.example.spamclassifier.api.request.LogInRequest;
import org.springframework.security.core.Authentication;

public interface AuthService {

    Authentication authenticate(LogInRequest request);
}
