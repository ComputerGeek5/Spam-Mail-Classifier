package com.example.spamclassifier.controller;

import com.example.spamclassifier.api.mapper.ComposeRequestMapper;
import com.example.spamclassifier.api.request.ComposeRequest;
import com.example.spamclassifier.api.response.BodyResponse;
import com.example.spamclassifier.api.response.resource.MailResponse;
import com.example.spamclassifier.dto.MailDTO;
import com.example.spamclassifier.service.abst.MailService;
import com.example.spamclassifier.util.annotation.BaseURL;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@BaseURL
public class MailController {

    private final MailService mailService;

    @Autowired
    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping(value = "/compose", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public BodyResponse compose(@RequestBody ComposeRequest composeRequest) {
        BodyResponse response;

        try {
            MailDTO mail = ComposeRequestMapper.INSTANCE.toDTO(composeRequest);
            mail = mailService.save(mail);
            MailResponse mailResponse = MailResponse.builder()
                    .fromDTO(mail)
                    .build();

            response = new BodyResponse(mailResponse)
                    .message("Mail sent successfully.")
                    .status(HttpStatus.OK.value());
        } catch (Exception e) {
            log.error("", e);
            response = new BodyResponse()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message("Something went wrong, please try again !");
        }

        return response;
    }

    @PostMapping(value = "/mark/{id}", produces = "application/json")
    @ResponseBody
    public BodyResponse mark(@PathVariable(value = "id") Long id, @RequestParam(value = "spam", required = false) Boolean spam) {
        BodyResponse response;

        try {
            MailDTO mail = mailService.find(id);
            mail.setSpam(spam);
            mailService.save(mail);

            response = new BodyResponse()
                    .message("Mail changed successfully.")
                    .status(HttpStatus.OK.value());
        } catch (Exception e) {
            log.error("", e);
            response = new BodyResponse()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message("Something went wrong, please try again !");
        }

        return response;
    }

    @PostMapping(value = "/delete/{id}", produces = "application/json")
    @ResponseBody
    public BodyResponse delete(@PathVariable(value = "id") Long id) {
        BodyResponse response;

        try {
            mailService.delete(id);

            response = new BodyResponse()
                    .message("Mail deleted.")
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
