package com.example.electronic_medical_book.service.Impl;

import com.example.electronic_medical_book.dto.MedicalBillDTO;
import com.example.electronic_medical_book.dto.PatientDTO;
import com.example.electronic_medical_book.entity.MedicalBill;
import com.example.electronic_medical_book.entity.Patient;
import com.example.electronic_medical_book.exception.RequestException;
import com.example.electronic_medical_book.mapper.MedicalBillMapper;
import com.example.electronic_medical_book.mapper.PatientMapper;
import com.example.electronic_medical_book.repository.PatientRepository;
import com.example.electronic_medical_book.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientMapper patientMapper;

    @Autowired
    private MedicalBillMapper medicalBillMapper;
    @Override
    public Patient findById(Long id) throws Exception {
        Optional<Patient> patient = patientRepository.findById(id);
        if (patient.isEmpty()){
            throw new RequestException("Not found this Patient: id" + id);
        }
        return patientRepository.findById(id).get();

    }

    @Override
    public void delete(Long id) throws Exception {
        this.patientRepository.delete(this.patientRepository.findById(id)
                .orElseThrow(() -> new RequestException("Not Found")));
    }

    @Override
    public PatientDTO create(Patient patient) {
        return patientMapper.toPatientDTO(this.patientRepository.save(patient));
    }

    @Override
    public PatientDTO update(PatientDTO patientDTO, Long id) throws Exception {
        Patient local = this.patientRepository.findById(id)
                .orElseThrow(() -> new RequestException("Not f1ound"));
        patientMapper.updateEntity(patientDTO, local);
        return (patientMapper.toPatientDTO((patientRepository.save(local))));
    }

    @Override
    public List<PatientDTO> findByName(String name) {
        List<Patient> patients = this.patientRepository.searchPatientByName("%" + name + "%");
        if (patients.isEmpty()) {
            throw new RequestException("No data!, please try again!");
        } else {
            List<PatientDTO> patientDTOList = this.patientMapper.toPatientDTOs(patients);
            return patientDTOList;
        }
    }

    @Override
    public List<MedicalBillDTO> findAllMedicalBillofPateint(Long id) {
        List<MedicalBill> medicalBills = this.patientRepository.filterMedicalBillByID(id);
        if (medicalBills.isEmpty()) {
            throw new RequestException("No data!, please try again!");
        } else {
            List<MedicalBillDTO> medicalBillDTOS = this.medicalBillMapper.toMedicalBillDTOs(medicalBills);
            return medicalBillDTOS;
        }
    }
}
