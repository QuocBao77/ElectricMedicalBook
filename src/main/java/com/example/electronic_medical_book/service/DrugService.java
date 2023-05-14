package com.example.electronic_medical_book.service;

import com.example.electronic_medical_book.dto.DrugDTO;
import com.example.electronic_medical_book.dto.MedicalBillDTO;
import com.example.electronic_medical_book.dto.MedicalBillDetailDTO;
import com.example.electronic_medical_book.dto.PatientDTO;
import com.example.electronic_medical_book.entity.Drug;
import com.example.electronic_medical_book.entity.MedicalBillDetail;

import java.util.List;

public interface DrugService {
    Drug findById (Long id) throws Exception;

    void delete (Long id) throws Exception;

    DrugDTO create(Drug drug);

    DrugDTO update(DrugDTO DrugDTO, Long id) throws Exception;

    List<DrugDTO> findByName(String name);

    List<MedicalBillDetailDTO> findAllMedicalBillDetailofDrug(Long id);
}
