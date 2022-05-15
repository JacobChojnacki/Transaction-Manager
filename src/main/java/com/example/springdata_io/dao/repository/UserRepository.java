package com.example.springdata_io.dao.repository;

import com.example.springdata_io.dao.entity.UserDto;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserDto, Long> {

    UserDto getUserDtoByName(String name);
}
