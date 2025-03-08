package com.hbdev.userservice.services;

import com.hbdev.userservice.entities.Role;
import com.hbdev.userservice.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RoleInitService implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public RoleInitService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        initializeRoles();
    }

    private void initializeRoles() {
        List<String> roleNames = Arrays.asList("ROLE_ADMIN", "ROLE_USER", "ROLE_NURSE", "ROLE_PATIENT");

        for (String roleName : roleNames) {
            if (!roleRepository.existsByName(roleName)) {
                Role role = new Role(roleName);
                roleRepository.save(role);
            }
        }
    }
}