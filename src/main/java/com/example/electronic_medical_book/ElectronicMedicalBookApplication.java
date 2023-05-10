package com.example.electronic_medical_book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class ElectronicMedicalBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElectronicMedicalBookApplication.class, args);
    }

}
