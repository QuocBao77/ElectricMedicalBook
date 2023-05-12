package com.example.electronic_medical_book.repository;

import com.example.electronic_medical_book.entity.MedicalBill;
import com.example.electronic_medical_book.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("select s from Patient s where s.name like :name")
    List<Patient> searchPatientByName(@Param("name") String name);

    @Query("select m from Patient s join MedicalBill m where s.id = m.patient.id and s.id = :id")
    List<MedicalBill> filterMedicalBillByID(@Param("id") Long id);


}
