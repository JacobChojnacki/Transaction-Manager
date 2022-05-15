package com.example.springdata_io.service;

import com.example.springdata_io.dao.entity.UserDto;
import com.example.springdata_io.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto findUserByName(String name) {
        return userRepository.getUserDtoByName(name);
    }
    public UserDto addUser(UserDto userDto) {
        return userRepository.save(userDto);
    }
}
