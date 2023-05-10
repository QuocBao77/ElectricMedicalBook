package com.example.electronic_medical_book.repository;

import com.example.electronic_medical_book.entity.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrugRepository extends JpaRepository<Drug, Long> {
}
