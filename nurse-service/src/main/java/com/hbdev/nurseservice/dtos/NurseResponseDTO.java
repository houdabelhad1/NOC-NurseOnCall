package com.hbdev.nurseservice.dtos;

import lombok.Data;

import java.util.Set;

@Data
public class NurseResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String email;
    private Set<String> skills;
    private double hourlyRate;
    private double rating;
}