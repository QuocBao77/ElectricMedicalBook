package com.example.electronic_medical_book.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Patient")
public class Patient {
    @Id
    @Column(name = "ID_Patient")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "missing your name")
    @Column(name = "Name_Patient")
    private String name;

    @NotEmpty(message = "missing your sex")
    @Column(name = "Sex")
    private String sex;

    @Email(message = "email invalid")
    @NotEmpty(message = "missing your email")
    @Column(name = "Email")
    private String email;

    @NotEmpty(message = "missing your address")
    @Column(name = "Address")
    private String address;

    @NotEmpty(message = "missing your citizen identity card")
    @Column(name = "Citizen identity card")
    private String cccd;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.REMOVE)
    private List<MedicalBill> medicalBillList;
}
