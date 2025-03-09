package com.hbdev.nurseservice.web;

import com.hbdev.nurseservice.dtos.NurseRequestDTO;
import com.hbdev.nurseservice.dtos.NurseResponseDTO;
import com.hbdev.nurseservice.service.NurseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nurses")
@Tag(name = "Infirmière", description = "Gestion des infirmières")
public class NurseController {
    private final NurseService nurseService;

    public NurseController(NurseService nurseService) {
        this.nurseService = nurseService;
    }

    @PostMapping
    @Operation(
            summary = "Créer une nouvelle infirmière",
            description = "Ajoute une nouvelle infirmière en fournissant les informations requises"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Infirmière créée avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    public ResponseEntity<NurseResponseDTO> createNurse(@RequestBody NurseRequestDTO nurseRequestDTO) {
        NurseResponseDTO nurseResponseDTO = nurseService.createNurse(nurseRequestDTO);
        return ResponseEntity.ok(nurseResponseDTO);
    }

    @GetMapping("/search")
    @Operation(
            summary = "Rechercher des infirmières",
            description = "Recherche des infirmières en fonction de la localisation et des compétences"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Infirmières trouvées"),
            @ApiResponse(responseCode = "404", description = "Aucune infirmière trouvée")
    })
    public ResponseEntity<List<NurseResponseDTO>> searchNurses(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String skill) {
        List<NurseResponseDTO> nurses = nurseService.searchNurses(location, skill);
        return ResponseEntity.ok(nurses);
    }

    @PostMapping("/{nurseId}/book")
    @Operation(
            summary = "Réserver une infirmière",
            description = "Réserve une infirmière pour un patient à une date et heure spécifiques"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Réservation confirmée"),
            @ApiResponse(responseCode = "400", description = "Créneau non disponible ou requête invalide")
    })
    public ResponseEntity<String> bookNurse(
            @PathVariable Long nurseId,
            @RequestParam String patientId,
            @RequestParam String dateTime) {
        String response = nurseService.bookNurse(nurseId, patientId, dateTime);
        return ResponseEntity.ok(response);
    }
}

