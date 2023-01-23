package com.example.spamclassifier.api.response;

import com.example.spamclassifier.api.response.resource.MailResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SentResponse {

    private List<MailResponse> mails;
}
