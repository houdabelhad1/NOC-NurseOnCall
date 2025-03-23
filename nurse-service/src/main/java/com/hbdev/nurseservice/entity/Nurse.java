package com.hbdev.nurseservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Nurse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String email;

    @ElementCollection
    private Set<String> skills;

    private double hourlyRate; // Tarif
    private double rating; // Note moyenne

    // Nouveaux champs
    private String specialty;
    private int experience;
    private String certification;
    private String bio;
}