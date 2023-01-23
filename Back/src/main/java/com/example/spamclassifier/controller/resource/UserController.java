package com.example.spamclassifier.controller.resource;

import com.example.spamclassifier.api.response.BodyResponse;
import com.example.spamclassifier.api.response.resource.UserResponse;
import com.example.spamclassifier.dto.UserDTO;
import com.example.spamclassifier.service.abst.UserService;
import com.example.spamclassifier.util.annotation.BaseURL;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@BaseURL
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user", produces = "application/json")
    public BodyResponse getAllUsers() {
        BodyResponse response;

        try {
            List<UserResponse> userResponses = new ArrayList<>();

            List<UserDTO> users = userService.findAll();
            for (UserDTO user: users) {
                UserResponse userResponse = UserResponse.builder()
                        .fromDTO(user)
                        .password(null)
                        .build();

                userResponses.add(userResponse);
            }

            response = new BodyResponse(userResponses)
                    .message("Users retrieved successfully.")
                    .status(HttpStatus.OK.value());
        } catch (Exception e) {
            log.error("", e);
            response = new BodyResponse()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message("Something went wrong, please try again !");
        }

        return response;
    }
}
