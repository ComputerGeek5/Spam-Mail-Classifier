package com.example.spamclassifier.api.response.resource;

import com.example.spamclassifier.dto.MailDTO;
import com.example.spamclassifier.util.TimeUtil;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MailResponse {


    private Long id;
    private String subject;
    private String message;
    private String createdAt;
    private Boolean spam;
    private UserResponse sender;
    private UserResponse receiver;

    public static class MailResponseBuilder {

        public MailResponseBuilder fromDTO(MailDTO mail) {
            this.id = mail.getId();
            this.subject = mail.getSubject();
            this.message = mail.getMessage();
            this.createdAt = TimeUtil.formatDateTime(mail.getCreatedAt());
            this.spam = mail.getSpam();

            this.sender = UserResponse.builder()
                    .fromDTO(mail.getSender())
                    .build();

            this.receiver = UserResponse.builder()
                    .fromDTO(mail.getReceiver())
                    .build();

            return this;
        }
    }
}
