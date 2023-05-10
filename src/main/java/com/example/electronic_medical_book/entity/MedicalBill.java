package com.example.electronic_medical_book.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.bridge.IMessage;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MedicalBill")
public class MedicalBill {
    @Id
    @Column(name = "ID_MedicalBill")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Patient", referencedColumnName = "ID_Patient")
    private Patient patient;

    @DateTimeFormat()
    @NotEmpty(message = "missing your address")
    @Column(name = "Request_Datetime")
    private String request_date;

    @OneToOne
    @JoinColumn(name = "Doctor", referencedColumnName = "ID_Doctor")
    private Doctor doctor;

    @OneToOne(mappedBy = "medicalBill", cascade = CascadeType.REMOVE)
    private MedicalBillDetail medicalBillDetail;


}
