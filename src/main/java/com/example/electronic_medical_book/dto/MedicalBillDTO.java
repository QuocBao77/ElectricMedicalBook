package com.example.electronic_medical_book.dto;

import com.example.electronic_medical_book.entity.Patient;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@AllArgsConstructor
@Getter
@Setter
public class MedicalBillDTO {
    @Id
    private Long id;

    private PatientDTO patient;

    private String request_date;

    private DoctorDTO doctor;


}
