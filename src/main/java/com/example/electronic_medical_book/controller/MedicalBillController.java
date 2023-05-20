package com.example.electronic_medical_book.controller;

import com.example.electronic_medical_book.dto.DiseaseInformationDTO;
import com.example.electronic_medical_book.dto.DoctorDTO;
import com.example.electronic_medical_book.dto.MedicalBillDTO;
import com.example.electronic_medical_book.dto.MedicalBillDetailDTO;
import com.example.electronic_medical_book.entity.DiseaseInformation;
import com.example.electronic_medical_book.entity.Doctor;
import com.example.electronic_medical_book.entity.MedicalBill;
import com.example.electronic_medical_book.entity.MedicalBillDetail;
import com.example.electronic_medical_book.mapper.MedicalBillMapper;
import com.example.electronic_medical_book.repository.MedicalBillRepository;
import com.example.electronic_medical_book.service.MedicalBillService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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


    // API Browser
    @GetMapping("/medicalBill")
    public ModelAndView medicalBill(ModelAndView modelAndView) {
        List<MedicalBillDTO> medicalBillDTOS = medicalBillMapper.toMedicalBillDTOs(this.medicalBillRepository.findAll());

        modelAndView.addObject("medicalBillList", medicalBillDTOS);
        modelAndView.setViewName("medicalBill");
        return modelAndView;
    }

    @GetMapping("/addMedicalBill")
    public ModelAndView addMedicalBill(ModelAndView modelAndView) {
        modelAndView.addObject("medicalBill", new MedicalBillDTO());
        modelAndView.setViewName("addMedicalBill");
        return modelAndView;
    }

    @RequestMapping(value = "/editMedicalBill", method = RequestMethod.GET)
    public ModelAndView currentMedicalBill(@RequestParam(name = "id") Long id,
                                      ModelAndView modelAndView) throws Exception {
        modelAndView.addObject("medicalBill", medicalBillService.findById(id));
        modelAndView.setViewName("editMedicalBill");
        return modelAndView;
    }

    @PostMapping("/saveMedicalBill")
    public ModelAndView saveMedicalBill(@Valid @ModelAttribute("medicalBill") MedicalBill newMedicalBill,
                                   RedirectAttributes redirectAttributes,
                                   ModelAndView modelAndView,
                                   BindingResult bindingResult) {
        modelAndView.setViewName("redirect:/medicalBill/medicalBill");
        if (bindingResult.hasErrors()) {

            return modelAndView;
        }
        try {
            medicalBillService.create(newMedicalBill);

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Add MedicalBill not successful, please try again");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/deleteMedicalBill", method = RequestMethod.GET)
    public ModelAndView deleteMedicalBill(@RequestParam(name = "id") Long id,
                                                 ModelAndView modelAndView,
                                                 RedirectAttributes redirectAttributes) throws Exception {
        medicalBillService.delete(id);
        redirectAttributes.addFlashAttribute("success", "Removed Medical Bill successfully!");
        modelAndView.setViewName("redirect:/medicalBill/medicalBill");
        return modelAndView;

    }

    @RequestMapping(value = "/detailMedicalBill", method = RequestMethod.GET)
    public ModelAndView Detail(@RequestParam(name = "id") Long id,
                                     ModelAndView modelAndView,
                                     RedirectAttributes redirectAttributes) throws Exception {
        modelAndView.addObject("medicalBillDetail",medicalBillService.findMBDofMB(id));
        modelAndView.setViewName("detailMedicalBill");
        return modelAndView;
    }


    // API PostMan
    @GetMapping("/getAll")
    List<MedicalBillDTO> getListMedicalBill(){
        return this.medicalBillMapper.toMedicalBillDTOs(this.medicalBillRepository.findAll());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get/{id}")
    MedicalBillDTO findByID (@PathVariable(name = "id") Long id) throws Exception{
        return  this.medicalBillMapper.toMedicalBillDTO(medicalBillService.findById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    MedicalBillDTO create (@RequestBody MedicalBill medicalBill){
        return this.medicalBillService.create(medicalBill);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    MedicalBillDTO update (@RequestBody MedicalBillDTO medicalBillDTO, @PathVariable(name = "id") Long id) throws Exception{
        return this.medicalBillService.update(medicalBillDTO, id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    void delete (@PathVariable(name = "id") Long id) throws Exception{
        this.medicalBillService.delete(id);
    }

    @GetMapping("/findByID/{id}")
    List<MedicalBillDetailDTO> findMBByIDofP (@PathVariable(name = "id") Long id){
        return this.medicalBillService.findMBDofMB(id);
    }
}
