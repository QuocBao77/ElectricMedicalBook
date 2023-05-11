package com.example.electronic_medical_book.controller;

import com.example.electronic_medical_book.dto.MedicalBillDTO;
import com.example.electronic_medical_book.dto.MedicalBillDetailDTO;
import com.example.electronic_medical_book.entity.MedicalBill;
import com.example.electronic_medical_book.entity.MedicalBillDetail;
import com.example.electronic_medical_book.mapper.MedicalBillDetailMapper;
import com.example.electronic_medical_book.repository.MedicalBillDetailRepository;
import com.example.electronic_medical_book.service.MedicalBillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicalBillDetail")
public class MedicalBillDetailController {

    @Autowired
    private MedicalBillDetailMapper medicalBillDetailMapper;

    @Autowired
    private MedicalBillDetailRepository medicalBillDetailRepository;

    @Autowired
    private MedicalBillDetailService medicalBillDetailService;

    @GetMapping("/getAll")
    List<MedicalBillDetailDTO> getListMedicalBillDetail(){
        return this.medicalBillDetailMapper.toMedicalBillDetailDTOs(this.medicalBillDetailRepository.findAll());
    }

    @GetMapping("/get/{id}")
    MedicalBillDetailDTO findByID (@PathVariable(name = "id") Long id) throws Exception{
        return  this.medicalBillDetailMapper.toMedicalBillDetailDTO(medicalBillDetailService.findById(id));
    }

    @PostMapping("/create")
    MedicalBillDetailDTO create (@RequestBody MedicalBillDetail medicalBillDetail){
        return this.medicalBillDetailService.create(medicalBillDetail);
    }

    @PutMapping("/update/{id}")
    MedicalBillDetailDTO update (@RequestBody MedicalBillDetailDTO medicalBillDetailDTO, @PathVariable(name = "id") Long id) throws Exception{
        return this.medicalBillDetailService.update(medicalBillDetailDTO, id);
    }

    @DeleteMapping("/delete/{id}")
    void delete (@PathVariable(name = "id") Long id) throws Exception{
        this.medicalBillDetailService.delete(id);
    }
}
