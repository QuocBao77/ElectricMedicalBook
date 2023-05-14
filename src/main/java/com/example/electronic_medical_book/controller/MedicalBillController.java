package com.example.electronic_medical_book.controller;

import com.example.electronic_medical_book.dto.DoctorDTO;
import com.example.electronic_medical_book.dto.MedicalBillDTO;
import com.example.electronic_medical_book.dto.MedicalBillDetailDTO;
import com.example.electronic_medical_book.entity.Doctor;
import com.example.electronic_medical_book.entity.MedicalBill;
import com.example.electronic_medical_book.entity.MedicalBillDetail;
import com.example.electronic_medical_book.mapper.MedicalBillMapper;
import com.example.electronic_medical_book.repository.MedicalBillRepository;
import com.example.electronic_medical_book.service.MedicalBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicalBill")
public class MedicalBillController {

    @Autowired
    private MedicalBillService medicalBillService;

    @Autowired
    private MedicalBillRepository medicalBillRepository;

    @Autowired
    private MedicalBillMapper medicalBillMapper;

    @GetMapping("/getAll")
    List<MedicalBillDTO> getListMedicalBill(){
        return this.medicalBillMapper.toMedicalBillDTOs(this.medicalBillRepository.findAll());
    }

    @GetMapping("/get/{id}")
    MedicalBillDTO findByID (@PathVariable(name = "id") Long id) throws Exception{
        return  this.medicalBillMapper.toMedicalBillDTO(medicalBillService.findById(id));
    }

    @PostMapping("/create")
    MedicalBillDTO create (@RequestBody MedicalBill medicalBill){
        return this.medicalBillService.create(medicalBill);
    }

    @PutMapping("/update/{id}")
    MedicalBillDTO update (@RequestBody MedicalBillDTO medicalBillDTO, @PathVariable(name = "id") Long id) throws Exception{
        return this.medicalBillService.update(medicalBillDTO, id);
    }

    @DeleteMapping("/delete/{id}")
    void delete (@PathVariable(name = "id") Long id) throws Exception{
        this.medicalBillService.delete(id);
    }

    @GetMapping("findByID/{id}")
    List<MedicalBillDetailDTO> findMBByIDofP (@PathVariable(name = "id") Long id){
        return this.medicalBillService.findMBDofMB(id);
    }
}
