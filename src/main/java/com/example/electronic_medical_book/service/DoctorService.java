package com.example.electronic_medical_book.service;

import com.example.electronic_medical_book.dto.DoctorDTO;
import com.example.electronic_medical_book.dto.DrugDTO;
import com.example.electronic_medical_book.dto.PatientDTO;
import com.example.electronic_medical_book.entity.Doctor;
import com.example.electronic_medical_book.entity.Drug;

import java.util.List;

public interface DoctorService {
    Doctor findById (Long id) throws Exception;

    void delete (Long id) throws Exception;

    DoctorDTO create(Doctor doctor);

    DoctorDTO update(DoctorDTO doctorDTO, Long id) throws Exception;

    List<DoctorDTO> findByName(String name);
}
