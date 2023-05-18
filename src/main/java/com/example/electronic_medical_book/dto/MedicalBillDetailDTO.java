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
public class MedicalBillDetailDTO {

    @Id
    private Long id;

    private MedicalBillDTO medicalBill;

    private DiseaseInformationDTO diseaseInformation;

    private String datetime;

    private DrugDTO drug;
}
