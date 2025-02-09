package com.hbdev.userservice.repository;

import com.hbdev.userservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    @Override
    Optional<User> findById(Long aLong);
}
