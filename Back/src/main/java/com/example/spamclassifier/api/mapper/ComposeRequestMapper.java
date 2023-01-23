package com.example.spamclassifier.api.mapper;

import com.example.spamclassifier.api.request.ComposeRequest;
import com.example.spamclassifier.dto.MailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ComposeRequestMapper extends RequestDTOMapper<ComposeRequest, MailDTO> {

    ComposeRequestMapper INSTANCE = Mappers.getMapper(ComposeRequestMapper.class);
}
