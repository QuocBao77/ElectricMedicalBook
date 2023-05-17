package com.example.electronic_medical_book.dto;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DiseaseInformationDTO {
    @Id
    private Long id;

    private String name;

    private String illustration;

    private String description;

    private DoctorDTO doctor;
}
