package com.example.electronic_medical_book.repository;

import com.example.electronic_medical_book.entity.Drug;
import com.example.electronic_medical_book.entity.MedicalBill;
import com.example.electronic_medical_book.entity.MedicalBillDetail;
import com.example.electronic_medical_book.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrugRepository extends JpaRepository<Drug, Long> {

    @Query("select s from Drug s where s.name like :name")
    List<Drug> searchDrugByName(@Param("name") String name);

    @Query("select m from Drug s join MedicalBillDetail m where s.id = m.drug.id and s.id = :id")
    List<MedicalBillDetail> filterMedicalBillDetailByIDDrug(@Param("id") Long id);
}
