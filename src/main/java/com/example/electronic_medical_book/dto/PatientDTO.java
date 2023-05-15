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
public class PatientDTO {
    @Id
    private Long id;

    private String name;

    private String sex;

    private String email;

    private String address;

    private String cccd;
}
