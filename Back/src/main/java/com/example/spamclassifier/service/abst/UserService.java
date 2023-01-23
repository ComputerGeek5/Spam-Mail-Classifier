package com.example.spamclassifier.service.abst;

import com.example.spamclassifier.dto.UserDTO;
import com.example.spamclassifier.service.CRUDService;

import java.util.List;

public interface UserService extends CRUDService<UserDTO> {

    UserDTO findByUsername(String username);

    List<UserDTO> findAll();
}
