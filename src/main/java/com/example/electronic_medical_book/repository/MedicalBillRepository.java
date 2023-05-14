package com.example.electronic_medical_book.repository;

import com.example.electronic_medical_book.entity.MedicalBill;
import com.example.electronic_medical_book.entity.MedicalBillDetail;
import com.example.electronic_medical_book.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalBillRepository extends JpaRepository<MedicalBill, Long> {

    @Query("select m from MedicalBill s join MedicalBillDetail m where s.id = m.medicalBill.id and s.id = :id")
    List<MedicalBillDetail> filterMedicalBillByID(@Param("id") Long id);
}
