package com.example.electronic_medical_book.service;
import com.example.electronic_medical_book.dto.PatientDTO;
import com.example.electronic_medical_book.entity.Patient;
public interface PatientService {
    Patient findById (Long id) throws Exception;

    void delete (Long id) throws Exception;

    PatientDTO create(Patient patient);

    PatientDTO update(PatientDTO patientDTO, Long id) throws Exception;
}
