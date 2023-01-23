package com.example.spamclassifier.controller;

import com.example.spamclassifier.api.response.BodyResponse;
import com.example.spamclassifier.api.response.SentResponse;
import com.example.spamclassifier.api.response.resource.MailResponse;
import com.example.spamclassifier.dto.MailDTO;
import com.example.spamclassifier.dto.UserDTO;
import com.example.spamclassifier.service.abst.MailService;
import com.example.spamclassifier.service.abst.UserService;
import com.example.spamclassifier.util.annotation.BaseURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@BaseURL
@RestController
public class SentController {

    private final UserService userService;
    private final MailService mailService;

    @Autowired
    public SentController(UserService userService, MailService mailService) {
        this.userService = userService;
        this.mailService = mailService;
    }

    @GetMapping("/sent/{id}")
    @ResponseBody
    public BodyResponse getSentMails(@PathVariable(value = "id") Long id) {
        BodyResponse response;

        try {
            List<MailResponse> mailResponses = new ArrayList<>();

            UserDTO sender = userService.find(id);
            List<MailDTO> mails = mailService.findSent(sender);
            for (MailDTO mail: mails) {
                MailResponse mailResponse = MailResponse.builder()
                        .fromDTO(mail)
                        .build();

                mailResponses.add(mailResponse);
            }

            SentResponse sentResponse = SentResponse.builder()
                    .mails(mailResponses)
                    .build();

            response = new BodyResponse(sentResponse)
                    .message("Collected sent mails successfully.")
                    .status(HttpStatus.OK.value());
        } catch (Exception e) {
            response = new BodyResponse()
                    .message("Something went wrong, please try again !")
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return response;
    }
}
