package com.example.electronic_medical_book.service.Impl;

import com.example.electronic_medical_book.dto.DiseaseInformationDTO;
import com.example.electronic_medical_book.entity.DiseaseInformation;
import com.example.electronic_medical_book.entity.Doctor;
import com.example.electronic_medical_book.entity.Drug;
import com.example.electronic_medical_book.exception.RequestException;
import com.example.electronic_medical_book.mapper.DiseaseInformationMapper;
import com.example.electronic_medical_book.repository.DiseaseInformationRepository;
import com.example.electronic_medical_book.service.DiseaseInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DiseaseInfomationServiceImpl implements DiseaseInformationService {

    @Autowired
    private DiseaseInformationMapper diseaseInformationMapper;

    @Autowired
    private DiseaseInformationRepository diseaseInformationRepository;

    @Override
    public DiseaseInformation findById(Long id) throws Exception {
        Optional<DiseaseInformation> diseaseInformation = diseaseInformationRepository.findById(id);
        if (diseaseInformation.isEmpty()){
            throw new RequestException("Not found this Disease Information have id: " + id);
        }
        return diseaseInformationRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) throws Exception {
        this.diseaseInformationRepository.delete(this.diseaseInformationRepository.findById(id)
                .orElseThrow(() -> new RequestException("Not found this Disease Information have id: " + id)));
    }

    @Override
    public DiseaseInformationDTO create(DiseaseInformation diseaseInformation) {
        return diseaseInformationMapper.toDiseaseInformationDTO(this.diseaseInformationRepository.save(diseaseInformation));
    }

    @Override
    public DiseaseInformationDTO update(DiseaseInformationDTO diseaseInformationDTO, Long id) throws Exception {
        DiseaseInformation local = this.diseaseInformationRepository.findById(id)
                .orElseThrow(() -> new RequestException("Not found this Disease Information have id: " + id));
        diseaseInformationMapper.updateEntity(diseaseInformationDTO, local);
        return (diseaseInformationMapper.toDiseaseInformationDTO((diseaseInformationRepository.save(local))));
    }
}
