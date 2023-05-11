package com.example.electronic_medical_book.mapper;

import com.example.electronic_medical_book.dto.*;
import com.example.electronic_medical_book.entity.*;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MedicalBillDetailMapper {
    MedicalBillDetailMapper INSTANCE = Mappers.getMapper(MedicalBillDetailMapper.class);

    MedicalBillDetailDTO toMedicalBillDetailDTO(MedicalBillDetail medicalBillDetail);

    MedicalBillDetail toMedicalBillDetailEntity(MedicalBillDetailDTO medicalBillDetailDTO);

    @Mapping(target = "id", ignore = true)
    void updateEntity(MedicalBillDetailDTO medicalBillDetailDTO, @MappingTarget MedicalBillDetail medicalBillDetail);

    List<MedicalBillDetailDTO> toMedicalBillDetailDTOs(List<MedicalBillDetail> medicalBillDetails);

    MedicalBill toMedicalBillEntity(MedicalBillDTO medicalBillDTO);

    Drug toDrugEntity(DrugDTO drugDTO);

    DiseaseInformation toDiseaseInformationEntity(DiseaseInformationDTO diseaseInformationDTO);


    @AfterMapping
    default void updateDrug_DiseaseInformation_MedicalBill(@MappingTarget MedicalBillDetail medicalBillDetail, MedicalBillDetailDTO medicalBillDetailDTO) {
        MedicalBill medicalBill = toMedicalBillEntity(medicalBillDetailDTO.getMedicalBill());
        medicalBillDetail.setMedicalBill(medicalBill);

        Drug drug = toDrugEntity(medicalBillDetailDTO.getDrug());
        medicalBillDetail.setDrug(drug);

        DiseaseInformation diseaseInformation = toDiseaseInformationEntity(medicalBillDetailDTO.getDiseaseInformation());
        medicalBillDetail.setDiseaseInformation(diseaseInformation);
    }
}
