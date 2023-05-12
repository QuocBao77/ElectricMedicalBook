package com.example.electronic_medical_book.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Doctor")
public class Doctor {
    @Id
    @Column(name = "ID_Doctor")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "missing your name")
    @Column(name = "Name_Doctor")
    private String name;

    @Email(message = "email invalid")
    @NotEmpty(message = "missing your email")
    @Column(name = "Email")
    private String email;

    @NotEmpty(message = "missing your address")
    @Column(name = "Address")
    private String address;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.REMOVE)
    private List<MedicalBill> medicalBillList;
}
