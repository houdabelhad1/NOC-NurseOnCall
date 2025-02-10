package com.hbdev.userservice.dtos;

import lombok.Data;

import java.util.Set;

@Data
public class UserRequestDTO {
    private String username;
    private String email;
    private String password;
    private Set<String> roles;
}
