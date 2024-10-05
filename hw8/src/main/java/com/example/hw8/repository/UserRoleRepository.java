package com.example.hw8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.hw8.model.UserRole;
import java.util.List;


public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    List<UserRole> findByUserId(Long userId);
}
