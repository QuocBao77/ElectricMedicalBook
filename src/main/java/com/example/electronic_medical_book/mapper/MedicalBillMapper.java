package com.example.electronic_medical_book.mapper;

import com.example.electronic_medical_book.dto.DoctorDTO;
import com.example.electronic_medical_book.dto.DrugDTO;
import com.example.electronic_medical_book.dto.MedicalBillDTO;
import com.example.electronic_medical_book.dto.PatientDTO;
import com.example.electronic_medical_book.entity.Doctor;
import com.example.electronic_medical_book.entity.Drug;
import com.example.electronic_medical_book.entity.MedicalBill;
import com.example.electronic_medical_book.entity.Patient;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MedicalBillMapper {
    MedicalBillMapper INSTANCE = Mappers.getMapper(MedicalBillMapper.class);

    MedicalBillDTO toMedicalBillDTO(MedicalBill medicalBill);

    MedicalBill toMedicalBillEntity(MedicalBillDTO medicalBillDTO);

    @Mapping(target = "id", ignore = true)
    void updateEntity(MedicalBillDTO medicalBillDTO, @MappingTarget MedicalBill medicalBill);

    List<MedicalBillDTO> toMedicalBillDTOs(List<MedicalBill> medicalBills);

    Patient toPatientEntity(PatientDTO patientDTO);

    Doctor toDoctorEntity(DoctorDTO doctorDTO);
    @AfterMapping
    default void updatePatientandDoctor(@MappingTarget MedicalBill medicalBill, MedicalBillDTO medicalBillDTO) {
        Patient patient = toPatientEntity(medicalBillDTO.getPatient());
        medicalBill.setPatient(patient);

        Doctor doctor = toDoctorEntity(medicalBillDTO.getDoctor());
        medicalBill.setDoctor(doctor);
    }
}
