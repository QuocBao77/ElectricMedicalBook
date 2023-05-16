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
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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


    // API Browser
    @GetMapping("/doctor")
    public ModelAndView doctor(ModelAndView modelAndView) {
        List<DoctorDTO> doctorDTOS = doctorMapper.toDoctorDTOs(this.doctorRepository.findAll());

        modelAndView.addObject("doctorList", doctorDTOS);
        modelAndView.setViewName("doctor");
        return modelAndView;
    }

    @GetMapping("/addDoctor")
    public ModelAndView addDoctor(ModelAndView modelAndView) {
        modelAndView.addObject("doctor", new DoctorDTO());
        modelAndView.setViewName("addDoctor");
        return modelAndView;
    }

    @PostMapping("/saveDoctor")
    public ModelAndView saveDoctor(@Valid @ModelAttribute("doctor") Doctor newDoctor,
                                    RedirectAttributes redirectAttributes,
                                    ModelAndView modelAndView,
                                    BindingResult bindingResult) {
        modelAndView.setViewName("redirect:/doctor/doctor");
        if (bindingResult.hasErrors()) {

            return modelAndView;
        }
        try {
            doctorService.create(newDoctor);

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Add Doctor not successful, please try again");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/editDoctor", method = RequestMethod.GET)
    public ModelAndView currentDoctor(@RequestParam(name = "id") Long id,
                                       ModelAndView modelAndView) throws Exception {
        modelAndView.addObject("doctor", doctorService.findById(id));
        modelAndView.setViewName("editDoctor");
        return modelAndView;
    }

    @RequestMapping(value = "/deleteDoctor", method = RequestMethod.GET)
    public ModelAndView deleteDoctor(@RequestParam(name = "id") Long id,
                                      ModelAndView modelAndView,
                                      RedirectAttributes redirectAttributes) throws Exception {
        doctorService.delete(id);
        redirectAttributes.addFlashAttribute("success", "Removed doctor successfully!");
        modelAndView.setViewName("redirect:/doctor/doctor");
        return modelAndView;

    }
    @RequestMapping (value ="/searchDoctor", method = RequestMethod.GET)
    public ModelAndView searchDoctor(@RequestParam(name = "name") String name,
                                      ModelAndView modelAndView,
                                      RedirectAttributes redirectAttributes) {

        try {
            List<DoctorDTO> doctorDTOS = doctorService.findByName(name);

            modelAndView.addObject("doctorList", doctorDTOS);
            modelAndView.setViewName("doctor");
            return modelAndView;
        }
        catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("No data");
            modelAndView.setViewName("redirect:/doctor/doctor");
            return modelAndView;
        }
    }

    // API PostMan

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
