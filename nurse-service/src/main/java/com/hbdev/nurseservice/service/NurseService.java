package com.hbdev.nurseservice.service;

import com.hbdev.nurseservice.dtos.NurseRequestDTO;
import com.hbdev.nurseservice.dtos.NurseResponseDTO;
import com.hbdev.nurseservice.entity.Nurse;
import com.hbdev.nurseservice.repository.NurseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NurseService {
    private final NurseRepository nurseRepository;

    public NurseService(NurseRepository nurseRepository) {
        this.nurseRepository = nurseRepository;
    }

    public NurseResponseDTO createNurse(NurseRequestDTO nurseRequestDTO) {
        // Convertir NurseRequestDTO en entité Nurse
        Nurse nurse = new Nurse();
        nurse.setFirstName(nurseRequestDTO.getFirstName());
        nurse.setLastName(nurseRequestDTO.getLastName());
        nurse.setAddress(nurseRequestDTO.getAddress());
        nurse.setPhoneNumber(nurseRequestDTO.getPhoneNumber());
        nurse.setEmail(nurseRequestDTO.getEmail());
        nurse.setSkills(nurseRequestDTO.getSkills());
        nurse.setHourlyRate(nurseRequestDTO.getHourlyRate());
        nurse.setRating(0.0); // Initialiser la note moyenne à 0

        // Sauvegarder l'entité
        Nurse savedNurse = nurseRepository.save(nurse);

        // Convertir l'entité sauvegardée en NurseResponseDTO
        return convertToResponseDTO(savedNurse);
    }

    public List<NurseResponseDTO> searchNurses(String location, String skill) {
        // Implémenter la logique de recherche (exemple simplifié)
        List<Nurse> nurses;
        if (location != null && skill != null) {
            nurses = nurseRepository.findByAddressContainingAndSkillsContaining(location, skill);
        } else if (location != null) {
            nurses = nurseRepository.findByAddressContaining(location);
        } else if (skill != null) {
            nurses = nurseRepository.findBySkillsContaining(skill);
        } else {
            nurses = nurseRepository.findAll();
        }

        return nurses.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public String bookNurse(Long nurseId, String patientId, String dateTime) {
        // Implémenter la logique de réservation
        return "Réservation confirmée pour l'infirmière " + nurseId + " avec le patient " + patientId + " à " + dateTime;
    }

    private NurseResponseDTO convertToResponseDTO(Nurse nurse) {
        NurseResponseDTO nurseResponseDTO = new NurseResponseDTO();
        nurseResponseDTO.setId(nurse.getId());
        nurseResponseDTO.setFirstName(nurse.getFirstName());
        nurseResponseDTO.setLastName(nurse.getLastName());
        nurseResponseDTO.setAddress(nurse.getAddress());
        nurseResponseDTO.setPhoneNumber(nurse.getPhoneNumber());
        nurseResponseDTO.setEmail(nurse.getEmail());
        nurseResponseDTO.setSkills(nurse.getSkills());
        nurseResponseDTO.setHourlyRate(nurse.getHourlyRate());
        nurseResponseDTO.setRating(nurse.getRating());
        return nurseResponseDTO;
    }
}