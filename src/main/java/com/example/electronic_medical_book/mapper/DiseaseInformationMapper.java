package com.example.electronic_medical_book.mapper;

import com.example.electronic_medical_book.dto.DiseaseInformationDTO;
import com.example.electronic_medical_book.entity.DiseaseInformation;
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
}
