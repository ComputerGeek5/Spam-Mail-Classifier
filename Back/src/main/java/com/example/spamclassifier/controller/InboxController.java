package com.example.spamclassifier.controller;

import com.example.spamclassifier.api.response.BodyResponse;
import com.example.spamclassifier.api.response.InboxResponse;
import com.example.spamclassifier.api.response.resource.MailResponse;
import com.example.spamclassifier.dto.MailDTO;
import com.example.spamclassifier.dto.UserDTO;
import com.example.spamclassifier.service.abst.MailService;
import com.example.spamclassifier.service.abst.UserService;
import com.example.spamclassifier.util.annotation.BaseURL;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@BaseURL
@RestController
@Slf4j
public class InboxController {

    private final UserService userService;
    private final MailService mailService;

    @Autowired
    public InboxController(UserService userService, MailService mailService) {
        this.userService = userService;
        this.mailService = mailService;
    }

    @GetMapping(value = "/inbox/{userId}", produces = "application/json")
    @ResponseBody
    public BodyResponse getReceivedMails(@PathVariable(value = "userId") Long userId) {
        BodyResponse response;

        try {
            UserDTO receiver = userService.find(userId);

            List<MailResponse> mailResponses = new ArrayList<>();

            List<MailDTO> mails = mailService.findInbox(receiver);
            for (MailDTO mail: mails) {
                MailResponse mailResponse = MailResponse.builder()
                        .fromDTO(mail)
                        .build();

                mailResponses.add(mailResponse);
            }

            InboxResponse inboxResponse = InboxResponse.builder()
                    .mails(mailResponses)
                    .build();

            response = new BodyResponse(inboxResponse)
                    .message("Collected inbox successfully.")
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
