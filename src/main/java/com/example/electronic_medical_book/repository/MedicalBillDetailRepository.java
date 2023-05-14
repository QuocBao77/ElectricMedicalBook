package com.example.electronic_medical_book.repository;

import com.example.electronic_medical_book.entity.MedicalBillDetail;
import com.example.electronic_medical_book.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalBillDetailRepository extends JpaRepository<MedicalBillDetail, Long> {


}
