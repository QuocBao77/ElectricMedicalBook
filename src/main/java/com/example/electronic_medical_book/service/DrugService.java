package com.example.electronic_medical_book.service;

import com.example.electronic_medical_book.dto.DrugDTO;
import com.example.electronic_medical_book.entity.Drug;

public interface DrugService {
    Drug findById (Long id) throws Exception;

    void delete (Long id) throws Exception;

    DrugDTO create(Drug drug);

    DrugDTO update(DrugDTO DrugDTO, Long id) throws Exception;
}
