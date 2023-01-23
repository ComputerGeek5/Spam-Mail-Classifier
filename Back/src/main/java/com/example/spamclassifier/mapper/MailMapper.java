package com.example.spamclassifier.mapper;

import com.example.spamclassifier.dto.MailDTO;
import com.example.spamclassifier.model.Mail;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MailMapper extends ModelMapper<Mail, MailDTO> {
    MailMapper INSTANCE = Mappers.getMapper(MailMapper.class);
}