package com.example.electronic_medical_book.controller;

import com.example.electronic_medical_book.dto.MedicalBillDTO;
import com.example.electronic_medical_book.dto.MedicalBillDetailDTO;
import com.example.electronic_medical_book.dto.PatientDTO;
import com.example.electronic_medical_book.entity.Patient;
import com.example.electronic_medical_book.mapper.PatientMapper;
import com.example.electronic_medical_book.repository.PatientRepository;
import com.example.electronic_medical_book.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
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

    @GetMapping("/patient")
    public ModelAndView patient(ModelAndView modelAndView) {
        List<PatientDTO> patientDTOS = patientMapper.toPatientDTOs(this.patientRepository.findAll());

        modelAndView.addObject("patientList", patientDTOS);
        modelAndView.setViewName("patient");
        return modelAndView;
    }

    @GetMapping("/addPatient")
    public ModelAndView addPatient(ModelAndView modelAndView) {
        modelAndView.addObject("patient", new PatientDTO());
        modelAndView.setViewName("addPatient");
        return modelAndView;
    }

    @PostMapping("/savePatient")
    public ModelAndView savePatient(@Valid @ModelAttribute("patient") Patient newPatient,
                                    RedirectAttributes redirectAttributes,
                                    ModelAndView modelAndView,
                                    BindingResult bindingResult) {
        modelAndView.setViewName("redirect:/patient/patient");
        if (bindingResult.hasErrors()) {

            return modelAndView;
        }
        try {
            patientService.create(newPatient);

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Add Patient not successful, please try again");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/editPatient", method = RequestMethod.GET)
    public ModelAndView currentPatient(@RequestParam(name = "id") Long id,
                                       ModelAndView modelAndView) throws Exception {
        modelAndView.addObject("patient", patientService.findById(id));
        modelAndView.setViewName("editPatient");
        return modelAndView;
    }

    @RequestMapping(value = "/deletePatient", method = RequestMethod.GET)
    public ModelAndView deletePatient(@RequestParam(name = "id") Long id,
                                       ModelAndView modelAndView,
                                      RedirectAttributes redirectAttributes) throws Exception {
        patientService.delete(id);
        redirectAttributes.addFlashAttribute("success", "Removed customer successfully!");
        modelAndView.setViewName("redirect:/patient/patient");
        return modelAndView;

    }
    @RequestMapping (value ="/searchPatient", method = RequestMethod.GET)
    public ModelAndView searchPatient(@RequestParam(name = "name") String name,
                                    ModelAndView modelAndView) {
        List<PatientDTO> patientDTOS = patientService.findByName(name);

        modelAndView.addObject("patientList", patientDTOS);
        modelAndView.setViewName("patient");
        return modelAndView;
    }


//    @GetMapping("/products")
//    public ModelAndView getProducts(ModelAndView modelAndView) {
//        List<ProductDTO> products = productService.getAllProducts();
//        modelAndView.addObject("products", products);
//        modelAndView.setViewName("products");
//        return modelAndView;
//    }


    @GetMapping("/getAll")
    List<PatientDTO> getListPatient() {
        return this.patientMapper.toPatientDTOs(this.patientRepository.findAll());
    }

    @GetMapping("/get/{id}")
    PatientDTO findByID(@PathVariable(name = "id") Long id) throws Exception {
        return this.patientMapper.toPatientDTO(patientService.findById(id));
    }

    @PostMapping("/create")
    PatientDTO create(@RequestBody Patient patient) {
        return this.patientService.create(patient);
    }

    @PutMapping("/update/{id}")
    PatientDTO update(@RequestBody PatientDTO patientDTO, @PathVariable(name = "id") Long id) throws Exception {
        return this.patientService.update(patientDTO, id);
    }

    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable(name = "id") Long id) throws Exception {
        this.patientService.delete(id);
    }

    @GetMapping("findByName/{name}")
    List<PatientDTO> findByName(@PathVariable(name = "name") String name) {
        return this.patientService.findByName(name);
    }

    @GetMapping("findByID/{id}")
    List<MedicalBillDTO> findMBByIDofP(@PathVariable(name = "id") Long id) {
        return this.patientService.findAllMedicalBillofPateint(id);
    }

    @GetMapping("findMBDByID/{id}")
    List<MedicalBillDetailDTO> findMBDByIDofP (@PathVariable(name = "id") Long id){
        return this.patientService.findAllMedicalBillDetailofPateint(id);
    }


}
