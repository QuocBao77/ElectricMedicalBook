package com.example.electronic_medical_book.repository;

import com.example.electronic_medical_book.config.authenticate.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
