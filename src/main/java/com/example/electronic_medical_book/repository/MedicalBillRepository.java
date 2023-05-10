package com.example.electronic_medical_book.repository;

import com.example.electronic_medical_book.entity.MedicalBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalBillRepository extends JpaRepository<MedicalBill, Long> {
}
