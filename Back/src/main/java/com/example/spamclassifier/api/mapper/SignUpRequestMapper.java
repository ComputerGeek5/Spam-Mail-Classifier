package com.example.spamclassifier.api.mapper;

import com.example.spamclassifier.api.request.LogInRequest;
import com.example.spamclassifier.api.request.SignUpRequest;
import com.example.spamclassifier.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SignUpRequestMapper extends RequestDTOMapper<SignUpRequest, UserDTO> {

    SignUpRequestMapper INSTANCE = Mappers.getMapper(SignUpRequestMapper.class);
}
