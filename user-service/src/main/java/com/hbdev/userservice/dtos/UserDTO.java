package com.hbdev.userservice.dtos;

import lombok.Data;
import java.util.Set;

@Data
public class UserDTO {
    private String username;
    private String password;
    private String email;
    private Set<String> roles;
}
