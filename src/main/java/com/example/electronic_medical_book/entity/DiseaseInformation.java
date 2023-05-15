package com.example.electronic_medical_book.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DiseaseInformation")

public class DiseaseInformation {

    @Id
    @Column(name = "ID_DiseaseInformation")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "missing your name")
    @Column(name = "Name_Disease")
    private String name;

    @NotEmpty(message = "missing your description")
    @Column(name = "Illustration")
    private String illustration;

    @NotEmpty(message = "missing your description")
    @Column(name = "Description")
    private String description;

    @OneToOne
    @JoinColumn(name = "Doctor", referencedColumnName = "ID_Doctor")
    private Doctor doctor;


    @OneToOne(mappedBy = "diseaseInformation", cascade = CascadeType.REMOVE)
    private MedicalBillDetail medicalBillDetail;

}
