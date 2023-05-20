package com.example.electronic_medical_book.controller;

import com.example.electronic_medical_book.dto.MedicalBillDTO;
import com.example.electronic_medical_book.dto.MedicalBillDetailDTO;
import com.example.electronic_medical_book.entity.MedicalBill;
import com.example.electronic_medical_book.entity.MedicalBillDetail;
import com.example.electronic_medical_book.mapper.MedicalBillDetailMapper;
import com.example.electronic_medical_book.repository.MedicalBillDetailRepository;
import com.example.electronic_medical_book.service.MedicalBillDetailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
@RequestMapping("/medicalBillDetail")
public class MedicalBillDetailController {

    @Autowired
    private MedicalBillDetailMapper medicalBillDetailMapper;

    @Autowired
    private MedicalBillDetailRepository medicalBillDetailRepository;

    @Autowired
    private MedicalBillDetailService medicalBillDetailService;



    // API Browser
    @GetMapping("/medicalBillDetail")
    public ModelAndView medicalBillDetail(ModelAndView modelAndView) {
        List<MedicalBillDetailDTO> medicalBillDetailDTOS = medicalBillDetailMapper.toMedicalBillDetailDTOs(this.medicalBillDetailRepository.findAll());

        modelAndView.addObject("medicalBillDetailList", medicalBillDetailDTOS);
        modelAndView.setViewName("medicalBillDetail");
        return modelAndView;
    }

    @GetMapping("/addMedicalBillDetail")
    public ModelAndView addMedicalBillDetail(ModelAndView modelAndView) {
        modelAndView.addObject("medicalBillDetail", new MedicalBillDetailDTO());
        modelAndView.setViewName("addMedicalBillDetail");
        return modelAndView;
    }

    @RequestMapping(value = "/editMedicalBillDetail", method = RequestMethod.GET)
    public ModelAndView currentMedicalBillDetail(@RequestParam(name = "id") Long id,
                                           ModelAndView modelAndView) throws Exception {
        modelAndView.addObject("medicalBillDetail", medicalBillDetailService.findById(id));
        modelAndView.setViewName("editMedicalBillDetail");
        return modelAndView;
    }
    @PostMapping("/saveMedicalBillDetail")
    public ModelAndView saveMedicalBillDetail(@Valid @ModelAttribute("medicalBillDetail") MedicalBillDetail newMedicalBillDetail,
                                        RedirectAttributes redirectAttributes,
                                        ModelAndView modelAndView,
                                        BindingResult bindingResult) {
        modelAndView.setViewName("redirect:/medicalBillDetail/medicalBillDetail");
        if (bindingResult.hasErrors()) {

            return modelAndView;
        }
        try {
            medicalBillDetailService.create(newMedicalBillDetail);

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Add MedicalBill Detail not successful, please try again");
        }
        return modelAndView;
    }

    // API PostMan
    @GetMapping("/getAll")
    List<MedicalBillDetailDTO> getListMedicalBillDetail(){
        return this.medicalBillDetailMapper.toMedicalBillDetailDTOs(this.medicalBillDetailRepository.findAll());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get/{id}")
    MedicalBillDetailDTO findByID (@PathVariable(name = "id") Long id) throws Exception{
        return  this.medicalBillDetailMapper.toMedicalBillDetailDTO(medicalBillDetailService.findById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    MedicalBillDetailDTO create (@RequestBody MedicalBillDetail medicalBillDetail){
        return this.medicalBillDetailService.create(medicalBillDetail);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    MedicalBillDetailDTO update (@RequestBody MedicalBillDetailDTO medicalBillDetailDTO, @PathVariable(name = "id") Long id) throws Exception{
        return this.medicalBillDetailService.update(medicalBillDetailDTO, id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    void delete (@PathVariable(name = "id") Long id) throws Exception{
        this.medicalBillDetailService.delete(id);
    }
}
