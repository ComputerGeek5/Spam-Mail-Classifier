package com.example.spamclassifier.mapper;

public interface ModelMapper<Entity, DTO> {

    Entity toEntity(DTO dto);

    DTO toDTO(Entity entity);
}
