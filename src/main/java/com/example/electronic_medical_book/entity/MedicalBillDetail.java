package com.example.electronic_medical_book.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.bridge.IMessage;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MedicalBillDetail")
public class MedicalBillDetail {

    @Id
    @Column(name = "ID_MedicalBillDetail")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "MedicalBill", referencedColumnName = "ID_MedicalBill")
    private MedicalBill medicalBill;

    @OneToOne
    @JoinColumn(name = "DiseaseInformation", referencedColumnName = "ID_DiseaseInformation")
    private DiseaseInformation diseaseInformation;

    @DateTimeFormat
    @NotEmpty(message = "missing your date")
    @Column(name = "DateTime")
    private String datetime;

    @OneToOne
    @JoinColumn(name = "Drug", referencedColumnName = "ID_Drug")
    private Drug drug;
}
