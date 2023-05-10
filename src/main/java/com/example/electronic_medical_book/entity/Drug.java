package com.example.electronic_medical_book.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import javax.validation.constraints.NotEmpty;

import jakarta.persistence.*;
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
@Table(name = "Drug")
public class Drug {
    @Id
    @Column(name = "ID_Drug")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "missing your name")
    @Column(name = "Name_Drug")
    private String name;

    @NotEmpty(message = "missing your dosage")
    @Column(name = "Dosage")
    private String dosage;

    @NotEmpty(message = "missing your used time")
    @Column(name = "Used_Time")
    private String used_time;

    @OneToOne(mappedBy = "drug", cascade = CascadeType.REMOVE)
    private MedicalBillDetail medicalBillDetail;

//    @ManyToMany(mappedBy = "drug", cascade = CascadeType.REMOVE)
//    private List<MedicalBillDetail> medicalBillDetailList;

}
