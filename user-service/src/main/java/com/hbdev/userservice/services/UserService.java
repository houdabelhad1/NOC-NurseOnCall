package com.hbdev.userservice.services;

import com.hbdev.userservice.dtos.UserDTO;
import com.hbdev.userservice.dtos.UserRequestDTO;
import com.hbdev.userservice.entities.Role;
import com.hbdev.userservice.entities.User;
import com.hbdev.userservice.repository.RoleRepository;
import com.hbdev.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public Optional<UserDTO> findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(this::convertToDTO);
    }

    public Optional<UserDTO> findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(this::convertToDTO);
    }

    public UserDTO saveUser(UserRequestDTO userRequestDTO) {
        // Récupérer les rôles par leurs noms
        Set<Role> roles = userRequestDTO.getRoleNames().stream()
                .map(roleName -> roleRepository.findByName(roleName)
                        .orElseThrow(() -> new RuntimeException("Rôle non trouvé: " + roleName)))
                .collect(Collectors.toSet());

        // Créer l'utilisateur avec les rôles
        User user = new User();
        user.setUsername(userRequestDTO.getUsername());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
        user.setRoles(roles);

        // Sauvegarder l'utilisateur
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    public UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());

        // Convertir les rôles en noms de rôles
        Set<String> roleNames = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
        userDTO.setRoles(roleNames);

        return userDTO;
    }
}