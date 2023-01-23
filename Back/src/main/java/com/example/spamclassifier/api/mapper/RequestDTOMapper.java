package com.example.spamclassifier.api.mapper;

public interface RequestDTOMapper<Request, DTO> {

    DTO toDTO(Request request);
}
