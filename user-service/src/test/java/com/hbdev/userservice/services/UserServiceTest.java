package com.hbdev.userservice.services;

import com.hbdev.userservice.dtos.UserDTO;
import com.hbdev.userservice.dtos.UserRequestDTO;
import com.hbdev.userservice.entities.Role;
import com.hbdev.userservice.entities.User;
import com.hbdev.userservice.repository.RoleRepository;
import com.hbdev.userservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByUsername_UserExists() {
        // Arrange
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("password");
        user.setRoles(Set.of(new Role("ROLE_USER")));
        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));

        // Act
        Optional<UserDTO> result = userService.findByUsername("testUser");

        // Assert
        assertTrue(result.isPresent());
        assertEquals("testUser", result.get().getUsername());
        assertTrue(result.get().getRoles().contains("ROLE_USER"));
    }

    @Test
    void testFindByUsername_UserNotExists() {
        // Arrange
        when(userRepository.findByUsername("unknownUser")).thenReturn(Optional.empty());

        // Act
        Optional<UserDTO> result = userService.findByUsername("unknownUser");

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void testSaveUser() {
        // Arrange
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setUsername("newUser");
        userRequestDTO.setPassword("securePass");

        User savedUser = new User();
        savedUser.setUsername("newUser");
        savedUser.setPassword("securePass");

        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        // Act
        UserDTO result = userService.saveUser(userRequestDTO);

        // Assert
        assertNotNull(result);
        assertEquals("newUser", result.getUsername());
    }
}
