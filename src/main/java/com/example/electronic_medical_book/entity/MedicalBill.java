package com.example.electronic_medical_book.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.bridge.IMessage;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
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


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Request_Datetime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    protected Date requestDate;

    @ManyToOne
    @JoinColumn(name = "Doctor", referencedColumnName = "ID_Doctor")
    private Doctor doctor;

    @OneToOne(mappedBy = "medicalBill", cascade = CascadeType.REMOVE)
    private MedicalBillDetail medicalBillDetail;


}
