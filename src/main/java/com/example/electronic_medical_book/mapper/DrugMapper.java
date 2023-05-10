package com.example.electronic_medical_book.mapper;

import com.example.electronic_medical_book.dto.DiseaseInformationDTO;
import com.example.electronic_medical_book.dto.DrugDTO;
import com.example.electronic_medical_book.entity.DiseaseInformation;
import com.example.electronic_medical_book.entity.Drug;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DrugMapper {
    DrugMapper INSTANCE = Mappers.getMapper(DrugMapper.class);

    DrugDTO toDrugDTO(Drug drug);

    Drug toDrugEntity(DrugDTO drugDTO);

    @Mapping(target = "id", ignore = true)
    void updateEntity(DrugDTO drugDTO, @MappingTarget Drug drug);

    List<DrugDTO> toDrugDTOs(List<Drug> drugs);
}
