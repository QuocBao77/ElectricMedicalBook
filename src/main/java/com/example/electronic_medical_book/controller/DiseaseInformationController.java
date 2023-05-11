package com.example.electronic_medical_book.controller;

import com.example.electronic_medical_book.dto.DiseaseInformationDTO;
import com.example.electronic_medical_book.dto.DrugDTO;
import com.example.electronic_medical_book.entity.DiseaseInformation;
import com.example.electronic_medical_book.entity.Drug;
import com.example.electronic_medical_book.mapper.DiseaseInformationMapper;
import com.example.electronic_medical_book.repository.DiseaseInformationRepository;
import com.example.electronic_medical_book.service.DiseaseInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diseaseInformation")
public class DiseaseInformationController {
    @Autowired
    private DiseaseInformationRepository diseaseInformationRepository;

    @Autowired
    private DiseaseInformationService diseaseInformationService;

    @Autowired
    private DiseaseInformationMapper diseaseInformationMapper;

    @GetMapping("/getAll")
    List<DiseaseInformationDTO> getListDiseaseInformation(){
        return this.diseaseInformationMapper.toDiseaseInformationDTOs(this.diseaseInformationRepository.findAll());
    }

    @GetMapping("/get/{id}")
    DiseaseInformationDTO findByID (@PathVariable(name = "id") Long id) throws Exception{
        return  this.diseaseInformationMapper.toDiseaseInformationDTO(diseaseInformationService.findById(id));
    }

    @PostMapping("/create")
    DiseaseInformationDTO create (@RequestBody DiseaseInformation diseaseInformation){

        return this.diseaseInformationService.create(diseaseInformation);
    }

    @PutMapping("/update/{id}")
    DiseaseInformationDTO update (@RequestBody DiseaseInformationDTO diseaseInformationDTO, @PathVariable(name = "id") Long id) throws Exception{
        return this.diseaseInformationService.update(diseaseInformationDTO, id);
    }

    @DeleteMapping("/delete/{id}")
    void delete (@PathVariable(name = "id") Long id) throws Exception{
        this.diseaseInformationService.delete(id);
    }
}
