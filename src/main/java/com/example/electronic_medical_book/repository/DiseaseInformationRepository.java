package com.example.electronic_medical_book.repository;

import com.example.electronic_medical_book.entity.DiseaseInformation;
import com.example.electronic_medical_book.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiseaseInformationRepository extends JpaRepository<DiseaseInformation, Long> {

    @Query("select s from DiseaseInformation s where s.name like :name")
    List<DiseaseInformation> searchDiseaseInformationByName(@Param("name") String name);
}
