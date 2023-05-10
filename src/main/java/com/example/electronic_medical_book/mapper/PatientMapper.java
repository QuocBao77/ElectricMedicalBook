package com.example.electronic_medical_book.mapper;

import com.example.electronic_medical_book.dto.PatientDTO;
import com.example.electronic_medical_book.entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

    PatientDTO toPatientDTO(Patient patient);

    Patient toPatientEntity(PatientDTO patientDTO);

    @Mapping(target = "id", ignore = true)
    void updateEntity(PatientDTO patientDTO, @MappingTarget Patient patient);

    List<PatientDTO> toDepartmentDTOs(List<Patient> patients);
}
