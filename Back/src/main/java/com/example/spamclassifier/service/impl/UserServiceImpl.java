package com.example.spamclassifier.service.impl;

import com.example.spamclassifier.dto.UserDTO;
import com.example.spamclassifier.mapper.UserMapper;
import com.example.spamclassifier.model.User;
import com.example.spamclassifier.repository.UserRepository;
import com.example.spamclassifier.service.abst.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO find(Long id) {
        return userRepository.findById(id)
                .map(UserMapper.INSTANCE::toDTO)
                .orElse(null);
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        User saved = userRepository.save(UserMapper.INSTANCE.toEntity(userDTO));
        return UserMapper.INSTANCE.toDTO(saved);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(UserMapper.INSTANCE::toDTO)
                .orElse(null);
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }
}
