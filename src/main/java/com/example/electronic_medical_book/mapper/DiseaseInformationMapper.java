package com.example.electronic_medical_book.mapper;

import com.example.electronic_medical_book.dto.DiseaseInformationDTO;
import com.example.electronic_medical_book.dto.DoctorDTO;
import com.example.electronic_medical_book.dto.MedicalBillDTO;
import com.example.electronic_medical_book.entity.DiseaseInformation;
import com.example.electronic_medical_book.entity.Doctor;
import com.example.electronic_medical_book.entity.MedicalBill;
import com.example.electronic_medical_book.entity.Patient;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper(componentModel = "spring")
public interface DiseaseInformationMapper {
    DiseaseInformationMapper INSTANCE = Mappers.getMapper(DiseaseInformationMapper.class);

    DiseaseInformationDTO toDiseaseInformationDTO(DiseaseInformation diseaseInformation);

    DiseaseInformation toDiseaseInformationEntity(DiseaseInformationDTO diseaseInformationDTO);

    @Mapping(target = "id", ignore = true)
    void updateEntity(DiseaseInformationDTO diseaseInformationDTO, @MappingTarget DiseaseInformation diseaseInformation);

    List<DiseaseInformationDTO> toDiseaseInformationDTOs(List<DiseaseInformation> diseaseInformations);

    Doctor toDoctorEntity(DoctorDTO doctorDTO);
    @AfterMapping
    default void updateDoctor(@MappingTarget DiseaseInformation diseaseInformation, DiseaseInformationDTO diseaseInformationDTO) {
        Doctor doctor = toDoctorEntity(diseaseInformationDTO.getDoctor());
        diseaseInformation.setDoctor(doctor);
    }
}
