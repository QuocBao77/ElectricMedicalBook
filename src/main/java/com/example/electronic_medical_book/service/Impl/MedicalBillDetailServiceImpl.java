package com.example.electronic_medical_book.service.Impl;

import com.example.electronic_medical_book.dto.MedicalBillDTO;
import com.example.electronic_medical_book.dto.MedicalBillDetailDTO;
import com.example.electronic_medical_book.entity.MedicalBill;
import com.example.electronic_medical_book.entity.MedicalBillDetail;
import com.example.electronic_medical_book.exception.RequestException;
import com.example.electronic_medical_book.mapper.MedicalBillDetailMapper;
import com.example.electronic_medical_book.mapper.MedicalBillMapper;
import com.example.electronic_medical_book.repository.MedicalBillDetailRepository;
import com.example.electronic_medical_book.repository.MedicalBillRepository;
import com.example.electronic_medical_book.service.MedicalBillDetailService;
import com.example.electronic_medical_book.service.MedicalBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedicalBillDetailServiceImpl implements MedicalBillDetailService {

    @Autowired
    private MedicalBillDetailRepository medicalBillDetailRepository;

    @Autowired
    private MedicalBillDetailMapper medicalBillDetailMapper;

    @Override
    public MedicalBillDetail findById(Long id) throws Exception {
        Optional<MedicalBillDetail> medicalBillDetail = medicalBillDetailRepository.findById(id);
        if (medicalBillDetail.isEmpty()){
            throw new RequestException("Not found this Medical Bill detail have id: " + id);
        }
        return medicalBillDetailRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) throws Exception {
        this.medicalBillDetailRepository.delete(this.medicalBillDetailRepository.findById(id)
                .orElseThrow(() -> new RequestException("Not found this Medical Bill Detail have id: " + id)));
    }

    @Override
    public MedicalBillDetailDTO create(MedicalBillDetail medicalBillDetail) {
        return medicalBillDetailMapper.toMedicalBillDetailDTO(this.medicalBillDetailRepository.save(medicalBillDetail));
    }

    @Override
    public MedicalBillDetailDTO update(MedicalBillDetailDTO medicalBillDetailDTO, Long id) throws Exception {
        MedicalBillDetail local = this.medicalBillDetailRepository.findById(id)
                .orElseThrow(() -> new RequestException("Not found this Medical Bill Detail have id: " + id));
        medicalBillDetailMapper.updateEntity(medicalBillDetailDTO, local);
        return (medicalBillDetailMapper.toMedicalBillDetailDTO((medicalBillDetailRepository.save(local))));
    }

}
