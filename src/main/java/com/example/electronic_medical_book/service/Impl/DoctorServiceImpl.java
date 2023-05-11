package com.example.electronic_medical_book.service.Impl;

import com.example.electronic_medical_book.dto.DoctorDTO;
import com.example.electronic_medical_book.entity.Doctor;
import com.example.electronic_medical_book.entity.Patient;
import com.example.electronic_medical_book.exception.RequestException;
import com.example.electronic_medical_book.mapper.DoctorMapper;
import com.example.electronic_medical_book.repository.DoctorRepository;
import com.example.electronic_medical_book.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;


    @Autowired
    private DoctorMapper doctorMapper;
    @Override
    public Doctor findById(Long id) throws Exception {
        Optional<Doctor> doctor = doctorRepository.findById(id);
        if (doctor.isEmpty()){
            throw new RequestException("Not found this Doctor have id: " + id);
        }
        return doctorRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) throws Exception {
        this.doctorRepository.delete(this.doctorRepository.findById(id)
                .orElseThrow(() -> new RequestException("Not found this Doctor have id: " + id)));
    }

    @Override
    public DoctorDTO create(Doctor doctor) {

        return doctorMapper.toDoctorDTO(this.doctorRepository.save(doctor));
    }

    @Override
    public DoctorDTO update(DoctorDTO doctorDTO, Long id) throws Exception {
        Doctor local = this.doctorRepository.findById(id)
                .orElseThrow(() -> new RequestException("Not found this Doctor have id: " + id));
        doctorMapper.updateEntity(doctorDTO, local);
        return (doctorMapper.toDoctorDTO((doctorRepository.save(local))));
    }
}
