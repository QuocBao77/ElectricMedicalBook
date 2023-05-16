package com.example.electronic_medical_book.controller;

import com.example.electronic_medical_book.dto.*;
import com.example.electronic_medical_book.entity.Doctor;
import com.example.electronic_medical_book.entity.Drug;
import com.example.electronic_medical_book.mapper.DrugMapper;
import com.example.electronic_medical_book.repository.DrugRepository;
import com.example.electronic_medical_book.service.DrugService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    // API Browser
    @GetMapping("/drug")
    public ModelAndView drug(ModelAndView modelAndView) {
        List<DrugDTO> drugDTOS = drugMapper.toDrugDTOs(this.drugRepository.findAll());

        modelAndView.addObject("drugList", drugDTOS);
        modelAndView.setViewName("drug");
        return modelAndView;
    }



    @RequestMapping (value ="/searchDrug", method = RequestMethod.GET)
    public ModelAndView searchDrug(@RequestParam(name = "name") String name,
                                     ModelAndView modelAndView,
                                     RedirectAttributes redirectAttributes) {

        try {
            List<DrugDTO> drugDTOS = drugService.findByName(name);

            modelAndView.addObject("drugList", drugDTOS);
            modelAndView.setViewName("drug");
            return modelAndView;
        }
        catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("No data");
            modelAndView.setViewName("redirect:/drug/drug");
            return modelAndView;
        }
    }

    // API PostMan
    @GetMapping("/getAll")
    List<DrugDTO> getListDrug(){
        return this.drugMapper.toDrugDTOs(this.drugRepository.findAll());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get/{id}")
    DrugDTO findByID (@PathVariable(name = "id") Long id) throws Exception{
        return  this.drugMapper.toDrugDTO(drugService.findById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    DrugDTO create (@RequestBody Drug drug){

        return this.drugService.create(drug);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    DrugDTO update (@RequestBody DrugDTO drugDTO, @PathVariable(name = "id") Long id) throws Exception{
        return this.drugService.update(drugDTO, id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    void delete (@PathVariable(name = "id") Long id) throws Exception{
        this.drugService.delete(id);
    }

    @GetMapping("findByName/{name}")
    List<DrugDTO> findByName (@PathVariable(name = "name") String name) {
        return this.drugService.findByName(name);
    }

    @GetMapping("findByID/{id}")
    List<MedicalBillDetailDTO> findMBDByIDofD (@PathVariable(name = "id") Long id){
        return this.drugService.findAllMedicalBillDetailofDrug(id);
    }
}
