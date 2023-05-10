package com.example.electronic_medical_book.dto;

import com.example.electronic_medical_book.entity.Patient;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class MedicalBillDTO {
    @Id
    private Long id;

    private PatientDTO patient;

    private Date requestDate;

    private DoctorDTO doctor;


}
