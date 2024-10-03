package com.example.hw7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.hw7.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);
}