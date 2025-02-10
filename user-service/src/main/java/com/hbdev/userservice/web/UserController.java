package com.hbdev.userservice.web;

import com.hbdev.userservice.dtos.UserDTO;
import com.hbdev.userservice.dtos.UserRequestDTO;
import com.hbdev.userservice.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Utilisateur", description = "Gestion des utilisateurs")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/username/{username}")
    @Operation(
            summary = "Rechercher un utilisateur par un username",
            description = "Permet de récupérer un utilisateur à partir de son nom d'utilisateur"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Utilisateur trouvé"),
            @ApiResponse(responseCode = "404", description = "Utilisateur non trouvé")
    })
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        return userService.findByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    @Operation(
            summary = "Créer un nouvel Utilisateur",
            description = "Ajoute un nouvel utilisateur en fournissant les informations requises "
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Utilisateur créé avec succés"),
            @ApiResponse(responseCode = "404", description = "Reqête invalide")
    })
    public ResponseEntity<UserDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        return ResponseEntity.ok(userService.saveUser(userRequestDTO));
    }

}
