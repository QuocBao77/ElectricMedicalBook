package com.example.electronic_medical_book.mapper;

import com.example.electronic_medical_book.dto.DoctorDTO;
import com.example.electronic_medical_book.dto.PatientDTO;
import com.example.electronic_medical_book.entity.Doctor;
import com.example.electronic_medical_book.entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    DoctorMapper INSTANCE = Mappers.getMapper(DoctorMapper.class);

    DoctorDTO toDoctorDTO(Doctor doctor);

    Doctor toDoctorEntity(DoctorDTO doctorDTO);

    @Mapping(target = "id", ignore = true)
    void updateEntity(DoctorDTO doctorDTO, @MappingTarget Doctor doctor);

    List<DoctorDTO> toDoctorDTOs(List<Doctor> doctors);
}
