package com.example.springdata_io.dao.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private String name;
    private String password;
    private String role;
}
