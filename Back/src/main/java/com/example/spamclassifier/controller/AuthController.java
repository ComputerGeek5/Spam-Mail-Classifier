package com.example.spamclassifier.controller;

import com.example.spamclassifier.api.mapper.SignUpRequestMapper;
import com.example.spamclassifier.api.request.LogInRequest;
import com.example.spamclassifier.api.request.SignUpRequest;
import com.example.spamclassifier.api.response.BodyResponse;
import com.example.spamclassifier.api.response.LogInResponse;
import com.example.spamclassifier.api.response.SignUpResponse;
import com.example.spamclassifier.api.response.resource.UserResponse;
import com.example.spamclassifier.dto.UserDTO;
import com.example.spamclassifier.enumerator.Role;
import com.example.spamclassifier.exception.CustomException;
import com.example.spamclassifier.service.abst.AuthService;
import com.example.spamclassifier.service.abst.UserService;
import com.example.spamclassifier.util.JWTUtil;
import com.example.spamclassifier.util.annotation.BaseURL;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@BaseURL
@RestController
@Slf4j
public class AuthController {

    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    private final AuthService authService;

    private final JWTUtil jwtUtil;

    @Autowired
    public AuthController(PasswordEncoder passwordEncoder, UserService userService, AuthService authService, JWTUtil jwtUtil) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping(value = "/signup", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public BodyResponse signUp(@RequestBody SignUpRequest request) {
        BodyResponse response;

        try {
            if (userService.findByUsername(request.getUsername()) != null) {
                throw new CustomException("Username taken !", HttpStatus.FORBIDDEN.value());
            }

            UserDTO user = SignUpRequestMapper.INSTANCE.toDTO(request);
            user.setRole(Role.USER);
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user = userService.save(user);

            UserResponse userResponse = UserResponse.builder()
                    .fromDTO(user)
                    .password(null)
                    .build();

            SignUpResponse signUpResponse = SignUpResponse.builder()
                    .user(userResponse)
                    .build();

            response = new BodyResponse(signUpResponse)
                    .status(HttpStatus.OK.value())
                    .message("Sign Up successful.");
        } catch (CustomException e) {
            response = new BodyResponse()
                    .status(e.getStatus())
                    .message(e.getMessage());
        } catch (Exception e) {
            log.error("", e);
            response = new BodyResponse()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message("Something went wrong, please try again !");
        }

        return response;
    }

    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public BodyResponse logIn(@RequestBody LogInRequest request) {
        BodyResponse response;

        try {
            authService.authenticate(request);
            UserDTO user = userService.findByUsername(request.getUsername());

            String token = jwtUtil.generateToken(user);
            UserResponse userResponse = UserResponse.builder()
                    .fromDTO(user)
                    .password(null)
                    .build();
            LogInResponse logInResponse = new LogInResponse(userResponse, token);

            response = new BodyResponse(logInResponse)
                    .status(HttpStatus.OK.value())
                    .message("Log In successful.");
        } catch(CustomException e) {
            log.error("", e);
            response = new BodyResponse()
                    .status(e.getStatus())
                    .message(e.getMessage());
        } catch (Exception e) {
            log.error("", e);
            response = new BodyResponse()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message("Something went wrong, please try again !");
        }

        return response;
    }
}
