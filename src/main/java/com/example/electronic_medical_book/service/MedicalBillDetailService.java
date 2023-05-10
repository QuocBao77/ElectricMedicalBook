package com.example.electronic_medical_book.service;

import com.example.electronic_medical_book.dto.MedicalBillDTO;
import com.example.electronic_medical_book.dto.MedicalBillDetailDTO;
import com.example.electronic_medical_book.entity.MedicalBill;
import com.example.electronic_medical_book.entity.MedicalBillDetail;

public interface MedicalBillDetailService {
    MedicalBillDetail findById (Long id) throws Exception;

    void delete (Long id) throws Exception;

    MedicalBillDetailDTO create(MedicalBillDetail medicalBillDetail);

    MedicalBillDetailDTO update(MedicalBillDetailDTO medicalBillDetailDTO, Long id) throws Exception;
}
