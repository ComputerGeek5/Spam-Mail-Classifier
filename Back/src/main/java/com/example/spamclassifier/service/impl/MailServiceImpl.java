package com.example.spamclassifier.service.impl;

import com.example.spamclassifier.dto.MailDTO;
import com.example.spamclassifier.dto.UserDTO;
import com.example.spamclassifier.mapper.MailMapper;
import com.example.spamclassifier.mapper.UserMapper;
import com.example.spamclassifier.model.Mail;
import com.example.spamclassifier.repository.MailRepository;
import com.example.spamclassifier.service.abst.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MailServiceImpl implements MailService {

    private final MailRepository mailRepository;

    @Autowired
    public MailServiceImpl(MailRepository mailRepository) {
        this.mailRepository = mailRepository;
    }

    @Override
    public MailDTO find(Long id) {
        return mailRepository.findById(id)
                .map(MailMapper.INSTANCE::toDTO)
                .orElse(null);
    }

    @Override
    public MailDTO save(MailDTO mailDTO) {
        Mail saved = mailRepository.save(MailMapper.INSTANCE.toEntity(mailDTO));
        return MailMapper.INSTANCE.toDTO(saved);
    }

    @Override
    public void delete(Long id) {
        mailRepository.deleteById(id);
    }

    @Override
    public List<MailDTO> findInbox(UserDTO receiver) {
        return mailRepository.findAllByReceiverAndSpamIsNullOrderByCreatedAtDesc(UserMapper.INSTANCE.toEntity(receiver))
                .stream()
                .map(MailMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MailDTO> findSent(UserDTO sender) {
        return mailRepository.findAllBySenderOrderByCreatedAtDesc(UserMapper.INSTANCE.toEntity(sender))
                .stream()
                .map(MailMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MailDTO> findJunk(UserDTO receiver) {
        return mailRepository.findAllByReceiverAndSpamIsTrueOrderByCreatedAtDesc(UserMapper.INSTANCE.toEntity(receiver))
                .stream()
                .map(MailMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MailDTO> findRead(UserDTO receiver) {
        return mailRepository.findAllByReceiverAndSpamIsFalseOrderByCreatedAtDesc(UserMapper.INSTANCE.toEntity(receiver))
                .stream()
                .map(MailMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }
}
