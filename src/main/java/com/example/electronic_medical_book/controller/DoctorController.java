package com.example.electronic_medical_book.controller;

import com.example.electronic_medical_book.dto.DoctorDTO;
import com.example.electronic_medical_book.dto.PatientDTO;
import com.example.electronic_medical_book.entity.Doctor;
import com.example.electronic_medical_book.entity.Patient;
import com.example.electronic_medical_book.mapper.DoctorMapper;
import com.example.electronic_medical_book.mapper.PatientMapper;
import com.example.electronic_medical_book.repository.DoctorRepository;
import com.example.electronic_medical_book.repository.PatientRepository;
import com.example.electronic_medical_book.service.DoctorService;
import com.example.electronic_medical_book.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DoctorMapper doctorMapper;


    @GetMapping("/getAll")
    List<DoctorDTO> getListDoctor(){
        return this.doctorMapper.toDoctorDTOs(this.doctorRepository.findAll());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get/{id}")
    DoctorDTO findByID (@PathVariable(name = "id") Long id) throws Exception{
        return  this.doctorMapper.toDoctorDTO(doctorService.findById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    DoctorDTO create (@RequestBody Doctor doctor){
        return this.doctorService.create(doctor);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    DoctorDTO update (@RequestBody DoctorDTO doctorDTO, @PathVariable(name = "id") Long id) throws Exception{
        return this.doctorService.update(doctorDTO, id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    void delete (@PathVariable(name = "id") Long id) throws Exception{
        this.doctorService.delete(id);
    }

    @GetMapping("findByName/{name}")
    List<DoctorDTO> findByName (@PathVariable(name = "name") String name) {
        return this.doctorService.findByName(name);
    }
}
