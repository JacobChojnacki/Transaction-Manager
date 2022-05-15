package com.example.springdata_io.security;

import com.example.springdata_io.dao.entity.User;
import com.example.springdata_io.dao.entity.UserDto;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserDtoBuilder {

    private final String name;
    private String passwordHash;
    private final String role;
    private final User user;

    public UserDtoBuilder(User user) {
        this.user = user;
        this.name = user.getName();
        this.passwordHash = getUserDto().getPasswordHash();
        this.role = user.getRole();
    }

    public String encodePassword() {
        PasswordEncoderConfig passwordEncoderConfig = new PasswordEncoderConfig();
        PasswordEncoder passwordEncoder = passwordEncoderConfig.passwordEncoder();
        String password = user.getPassword();
        passwordHash = passwordEncoder.encode(password);
        return passwordHash;
    }

    public UserDto getUserDto() {
        return new UserDto(name, encodePassword(), role);
    }
}
