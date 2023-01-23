package com.example.spamclassifier.service.abst;

import com.example.spamclassifier.dto.MailDTO;
import com.example.spamclassifier.dto.UserDTO;
import com.example.spamclassifier.service.CRUDService;

import java.util.List;

public interface MailService extends CRUDService<MailDTO> {

    List<MailDTO> findInbox(UserDTO receiver);

    List<MailDTO> findJunk(UserDTO receiver);

    List<MailDTO> findRead(UserDTO receiver);

    List<MailDTO> findSent(UserDTO sender);
}
