package com.example.electronic_medical_book.repository;

import com.example.electronic_medical_book.config.authenticate.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByUsername(String username);
}
