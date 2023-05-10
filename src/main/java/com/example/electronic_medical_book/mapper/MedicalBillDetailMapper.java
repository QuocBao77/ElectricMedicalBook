package com.example.electronic_medical_book.mapper;

import com.example.electronic_medical_book.dto.MedicalBillDTO;
import com.example.electronic_medical_book.dto.MedicalBillDetailDTO;
import com.example.electronic_medical_book.entity.MedicalBill;
import com.example.electronic_medical_book.entity.MedicalBillDetail;
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
}
