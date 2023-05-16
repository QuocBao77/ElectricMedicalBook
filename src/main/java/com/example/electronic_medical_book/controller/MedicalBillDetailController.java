package com.example.electronic_medical_book.controller;

import com.example.electronic_medical_book.dto.MedicalBillDTO;
import com.example.electronic_medical_book.dto.MedicalBillDetailDTO;
import com.example.electronic_medical_book.entity.MedicalBill;
import com.example.electronic_medical_book.entity.MedicalBillDetail;
import com.example.electronic_medical_book.mapper.MedicalBillDetailMapper;
import com.example.electronic_medical_book.repository.MedicalBillDetailRepository;
import com.example.electronic_medical_book.service.MedicalBillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get/{id}")
    MedicalBillDetailDTO findByID (@PathVariable(name = "id") Long id) throws Exception{
        return  this.medicalBillDetailMapper.toMedicalBillDetailDTO(medicalBillDetailService.findById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    MedicalBillDetailDTO create (@RequestBody MedicalBillDetail medicalBillDetail){
        return this.medicalBillDetailService.create(medicalBillDetail);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    MedicalBillDetailDTO update (@RequestBody MedicalBillDetailDTO medicalBillDetailDTO, @PathVariable(name = "id") Long id) throws Exception{
        return this.medicalBillDetailService.update(medicalBillDetailDTO, id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    void delete (@PathVariable(name = "id") Long id) throws Exception{
        this.medicalBillDetailService.delete(id);
    }
}
