package com.example.spamclassifier.mapper;

import com.example.spamclassifier.dto.UserDTO;
import com.example.spamclassifier.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper extends ModelMapper<User, UserDTO> {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
}