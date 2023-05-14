package com.example.electronic_medical_book.service;

import com.example.electronic_medical_book.dto.DiseaseInformationDTO;
import com.example.electronic_medical_book.dto.DoctorDTO;
import com.example.electronic_medical_book.entity.DiseaseInformation;

import java.util.List;

public interface DiseaseInformationService {

    DiseaseInformation findById (Long id) throws Exception;

    void delete (Long id) throws Exception;

    DiseaseInformationDTO create(DiseaseInformation diseaseInformation);

    DiseaseInformationDTO update(DiseaseInformationDTO diseaseInformationDTO, Long id) throws Exception;

    List<DiseaseInformationDTO> findByName(String name);
}
