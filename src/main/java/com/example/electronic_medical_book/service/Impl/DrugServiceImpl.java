package com.example.electronic_medical_book.service.Impl;

import com.example.electronic_medical_book.dto.DrugDTO;
import com.example.electronic_medical_book.dto.MedicalBillDTO;
import com.example.electronic_medical_book.dto.MedicalBillDetailDTO;
import com.example.electronic_medical_book.dto.PatientDTO;
import com.example.electronic_medical_book.entity.*;
import com.example.electronic_medical_book.exception.RequestException;
import com.example.electronic_medical_book.mapper.DrugMapper;
import com.example.electronic_medical_book.mapper.MedicalBillDetailMapper;
import com.example.electronic_medical_book.repository.DrugRepository;
import com.example.electronic_medical_book.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DrugServiceImpl implements DrugService {

    @Autowired
    private DrugRepository drugRepository;

    @Autowired
    private DrugMapper drugMapper;

    @Autowired
    private MedicalBillDetailMapper medicalBillDetailMapper;

    @Override
    public Drug findById(Long id) throws Exception {
        Optional<Drug> drug = drugRepository.findById(id);
        if (drug.isEmpty()){
            throw new RequestException("Not found this Drug have id: " + id);
        }
        return drugRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) throws Exception {
        this.drugRepository.delete(this.drugRepository.findById(id)
                .orElseThrow(() -> new RequestException("Not found this Drug have id: " + id)));
    }

    @Override
    public DrugDTO create(Drug drug) {
        return drugMapper.toDrugDTO(this.drugRepository.save(drug));
    }

    @Override
    public DrugDTO update(DrugDTO DrugDTO, Long id) throws Exception {
        Drug local = this.drugRepository.findById(id)
                .orElseThrow(() -> new RequestException("Not found this Drug have id: " + id));
        drugMapper.updateEntity(DrugDTO, local);
        return (drugMapper.toDrugDTO((drugRepository.save(local))));
    }

    @Override
    public List<DrugDTO> findByName(String name) {
        List<Drug> drugs = this.drugRepository.searchDrugByName("%" + name + "%");
        if (drugs.isEmpty()) {
            throw new RequestException("No data!, please try again!");
        } else {
            List<DrugDTO> drugDTOS = this.drugMapper.toDrugDTOs(drugs);
            return drugDTOS;
        }
    }

    @Override
    public List<MedicalBillDetailDTO> findAllMedicalBillDetailofDrug(Long id) {
        List<MedicalBillDetail> medicalBillDetails = this.drugRepository.filterMedicalBillDetailByIDDrug(id);
        if (medicalBillDetails.isEmpty()) {
            throw new RequestException("No data!, please try again!");
        } else {
            List<MedicalBillDetailDTO> medicalBillDetailDTOList = this.medicalBillDetailMapper.toMedicalBillDetailDTOs(medicalBillDetails);
            return medicalBillDetailDTOList;
        }
    }
}
