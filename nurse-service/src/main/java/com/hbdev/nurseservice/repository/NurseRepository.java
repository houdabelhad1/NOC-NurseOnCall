package com.hbdev.nurseservice.repository;

import com.hbdev.nurseservice.entity.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface NurseRepository extends JpaRepository<Nurse, Long> {
    List<Nurse> findBySkillsContaining(String skill);
    List<Nurse> findByAddressContaining(String location);
    List<Nurse> findByHourlyRateLessThanEqual(double maxHourlyRate);
    List<Nurse> findByAddressContainingAndSkillsContaining(String location, String skill);
}
