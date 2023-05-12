package com.example.electronic_medical_book.controller;

import com.example.electronic_medical_book.dto.MedicalBillDTO;
import com.example.electronic_medical_book.dto.PatientDTO;
import com.example.electronic_medical_book.entity.Patient;
import com.example.electronic_medical_book.mapper.PatientMapper;
import com.example.electronic_medical_book.repository.PatientRepository;
import com.example.electronic_medical_book.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientMapper patientMapper;

    @GetMapping("/getAll")
    List<PatientDTO> getListPatient(){
        return this.patientMapper.toPatientDTOs(this.patientRepository.findAll());
    }

    @GetMapping("/get/{id}")
    PatientDTO findByID (@PathVariable(name = "id") Long id) throws Exception{
        return  this.patientMapper.toPatientDTO(patientService.findById(id));
    }

    @PostMapping("/create")
    PatientDTO create (@RequestBody Patient patient){
        return this.patientService.create(patient);
    }

    @PutMapping("/update/{id}")
    PatientDTO update (@RequestBody PatientDTO patientDTO, @PathVariable(name = "id") Long id) throws Exception{
        return this.patientService.update(patientDTO, id);
    }

    @DeleteMapping("/delete/{id}")
    void delete (@PathVariable(name = "id") Long id) throws Exception{
        this.patientService.delete(id);
    }

    @GetMapping("findByName/{name}")
    List<PatientDTO> findByName (@PathVariable(name = "name") String name) {
        return this.patientService.findByName(name);
    }

    @GetMapping("findByID/{id}")
    List<MedicalBillDTO> findMBByIDofP (@PathVariable(name = "id") Long id){
        return this.patientService.findAllMedicalBillofPateint(id);
    }


}
