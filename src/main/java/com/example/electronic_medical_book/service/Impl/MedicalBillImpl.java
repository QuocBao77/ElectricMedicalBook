package com.example.electronic_medical_book.service.Impl;

import com.example.electronic_medical_book.dto.MedicalBillDTO;
import com.example.electronic_medical_book.entity.Doctor;
import com.example.electronic_medical_book.entity.MedicalBill;
import com.example.electronic_medical_book.exception.RequestException;
import com.example.electronic_medical_book.mapper.MedicalBillMapper;
import com.example.electronic_medical_book.repository.MedicalBillRepository;
import com.example.electronic_medical_book.service.MedicalBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedicalBillImpl implements MedicalBillService {

    @Autowired
    private MedicalBillMapper medicalBillMapper;

    @Autowired
    private MedicalBillRepository medicalBillRepository;

    @Override
    public MedicalBill findById(Long id) throws Exception {
        Optional<MedicalBill> medicalBill = medicalBillRepository.findById(id);
        if (medicalBill.isEmpty()){
            throw new RequestException("Not found this MedicalBill have id: " + id);
        }
        return medicalBillRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) throws Exception {
        this.medicalBillRepository.delete(this.medicalBillRepository.findById(id)
                .orElseThrow(() -> new RequestException("Not found this Medical Bill have id: " + id)));
    }

    @Override
    public MedicalBillDTO create(MedicalBill medicalBill) {
        return medicalBillMapper.toMedicalBillDTO(this.medicalBillRepository.save(medicalBill));
    }

    @Override
    public MedicalBillDTO update(MedicalBillDTO medicalBillDTO, Long id) throws Exception {
        MedicalBill local = this.medicalBillRepository.findById(id)
                .orElseThrow(() -> new RequestException("Not found this Medical Bill have id: " + id));
        medicalBillMapper.updateEntity(medicalBillDTO, local);
        return (medicalBillMapper.toMedicalBillDTO((medicalBillRepository.save(local))));
    }
}
