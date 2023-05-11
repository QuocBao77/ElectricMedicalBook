package com.example.electronic_medical_book.controller;

import com.example.electronic_medical_book.dto.DoctorDTO;
import com.example.electronic_medical_book.dto.DrugDTO;
import com.example.electronic_medical_book.entity.Doctor;
import com.example.electronic_medical_book.entity.Drug;
import com.example.electronic_medical_book.mapper.DrugMapper;
import com.example.electronic_medical_book.repository.DrugRepository;
import com.example.electronic_medical_book.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drug")
public class DrugController {

    @Autowired
    private DrugMapper drugMapper;

    @Autowired
    private DrugRepository drugRepository;

    @Autowired
    private DrugService drugService;

    @GetMapping("/getAll")
    List<DrugDTO> getListDrug(){
        return this.drugMapper.toDrugDTOs(this.drugRepository.findAll());
    }

    @GetMapping("/get/{id}")
    DrugDTO findByID (@PathVariable(name = "id") Long id) throws Exception{
        return  this.drugMapper.toDrugDTO(drugService.findById(id));
    }

    @PostMapping("/create")
    DrugDTO create (@RequestBody Drug drug){

        return this.drugService.create(drug);
    }

    @PutMapping("/update/{id}")
    DrugDTO update (@RequestBody DrugDTO drugDTO, @PathVariable(name = "id") Long id) throws Exception{
        return this.drugService.update(drugDTO, id);
    }

    @DeleteMapping("/delete/{id}")
    void delete (@PathVariable(name = "id") Long id) throws Exception{
        this.drugService.delete(id);
    }
}
