package com.example.electronic_medical_book.service.Impl;

import com.example.electronic_medical_book.dto.DrugDTO;
import com.example.electronic_medical_book.entity.Doctor;
import com.example.electronic_medical_book.entity.Drug;
import com.example.electronic_medical_book.exception.RequestException;
import com.example.electronic_medical_book.mapper.DrugMapper;
import com.example.electronic_medical_book.repository.DrugRepository;
import com.example.electronic_medical_book.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DrugServiceImpl implements DrugService {

    @Autowired
    private DrugRepository drugRepository;

    @Autowired
    private DrugMapper drugMapper;

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
}
