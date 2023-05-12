package com.example.electronic_medical_book.service;
import com.example.electronic_medical_book.dto.MedicalBillDTO;
import com.example.electronic_medical_book.dto.PatientDTO;
import com.example.electronic_medical_book.entity.MedicalBill;
import com.example.electronic_medical_book.entity.Patient;

import java.util.List;

public interface PatientService {
    Patient findById (Long id) throws Exception;

    void delete (Long id) throws Exception;

    PatientDTO create(Patient patient);

    PatientDTO update(PatientDTO patientDTO, Long id) throws Exception;

    List<PatientDTO> findByName(String name);

    List<MedicalBillDTO> findAllMedicalBillofPateint(Long id);
}
