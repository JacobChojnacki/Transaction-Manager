package com.example.springdata_io;

import com.example.springdata_io.dao.entity.User;
import com.example.springdata_io.dao.entity.UserDto;
import com.example.springdata_io.security.UserDtoBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDataIoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataIoApplication.class, args);

//        UserDtoBuilder userDtoBuilder = new UserDtoBuilder(new User("Jakub", "Jakub", "ROLE_ADMIN"));
//        UserDto userDto = userDtoBuilder.getUserDto();
//        System.out.println(userDto);
    }

}
