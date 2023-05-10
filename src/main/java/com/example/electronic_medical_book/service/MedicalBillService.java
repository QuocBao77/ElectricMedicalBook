package com.example.electronic_medical_book.service;

import com.example.electronic_medical_book.dto.MedicalBillDTO;
import com.example.electronic_medical_book.dto.PatientDTO;
import com.example.electronic_medical_book.entity.MedicalBill;
import com.example.electronic_medical_book.entity.Patient;

public interface MedicalBillService {
    MedicalBill findById (Long id) throws Exception;

    void delete (Long id) throws Exception;

    MedicalBillDTO create(MedicalBill medicalBill);

    MedicalBillDTO update(MedicalBillDTO medicalBillDTO, Long id) throws Exception;

}
