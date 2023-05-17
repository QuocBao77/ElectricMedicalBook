package com.example.electronic_medical_book.controller;

import com.example.electronic_medical_book.dto.DiseaseInformationDTO;
import com.example.electronic_medical_book.dto.DoctorDTO;
import com.example.electronic_medical_book.dto.DrugDTO;
import com.example.electronic_medical_book.entity.DiseaseInformation;
import com.example.electronic_medical_book.entity.Doctor;
import com.example.electronic_medical_book.entity.Drug;
import com.example.electronic_medical_book.mapper.DiseaseInformationMapper;
import com.example.electronic_medical_book.repository.DiseaseInformationRepository;
import com.example.electronic_medical_book.service.DiseaseInformationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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


    // API Browser
    @GetMapping("/diseaseInformation")
    public ModelAndView diseaseInformation(ModelAndView modelAndView) {
        List<DiseaseInformationDTO> diseaseInformationDTOS = diseaseInformationMapper.toDiseaseInformationDTOs(this.diseaseInformationRepository.findAll());

        modelAndView.addObject("diseaseInformationList", diseaseInformationDTOS);
        modelAndView.setViewName("diseaseInformation");
        return modelAndView;
    }

    @GetMapping("/addDiseaseInformation")
    public ModelAndView addDiseaseInformation(ModelAndView modelAndView) {
        modelAndView.addObject("diseaseInformation", new DiseaseInformationDTO());
        modelAndView.setViewName("addDiseaseInformation");
        return modelAndView;
    }

    @RequestMapping(value = "/editDiseaseInformation", method = RequestMethod.GET)
    public ModelAndView currentDoctor(@RequestParam(name = "id") Long id,
                                      ModelAndView modelAndView) throws Exception {
        modelAndView.addObject("diseaseInformation", diseaseInformationService.findById(id));
        modelAndView.setViewName("editDiseaseInformation");
        return modelAndView;
    }

    @PostMapping("/saveDiseaseInformation")
    public ModelAndView saveDoctor(@Valid @ModelAttribute("diseaseInformation") DiseaseInformation newDiseaseInformation,
                                   RedirectAttributes redirectAttributes,
                                   ModelAndView modelAndView,
                                   BindingResult bindingResult) {
        modelAndView.setViewName("redirect:/diseaseInformation/diseaseInformation");
        if (bindingResult.hasErrors()) {

            return modelAndView;
        }
        try {
            diseaseInformationService.create(newDiseaseInformation);

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Add DiseaseInformation not successful, please try again");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/deleteDiseaseInformation", method = RequestMethod.GET)
    public ModelAndView deleteDiseaseInformation(@RequestParam(name = "id") Long id,
                                     ModelAndView modelAndView,
                                     RedirectAttributes redirectAttributes) throws Exception {
        diseaseInformationService.delete(id);
        redirectAttributes.addFlashAttribute("success", "Removed diseaseInformation successfully!");
        modelAndView.setViewName("redirect:/diseaseInformation/diseaseInformation");
        return modelAndView;

    }

    @RequestMapping (value ="/searchDiseaseInformation", method = RequestMethod.GET)
    public ModelAndView searchDiseaseInformation(@RequestParam(name = "name") String name,
                                     ModelAndView modelAndView,
                                     RedirectAttributes redirectAttributes) {

        try {
            List<DiseaseInformationDTO> diseaseInformationDTOS = diseaseInformationService.findByName(name);

            modelAndView.addObject("diseaseInformationList", diseaseInformationDTOS);
            modelAndView.setViewName("diseaseInformation");
            return modelAndView;
        }
        catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("No data");
            modelAndView.setViewName("redirect:/diseaseInformation/diseaseInformation");
            return modelAndView;
        }
    }

    // API PostMan

    @GetMapping("/getAll")
    List<DiseaseInformationDTO> getListDiseaseInformation(){
        return this.diseaseInformationMapper.toDiseaseInformationDTOs(this.diseaseInformationRepository.findAll());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get/{id}")
    DiseaseInformationDTO findByID (@PathVariable(name = "id") Long id) throws Exception{
        return  this.diseaseInformationMapper.toDiseaseInformationDTO(diseaseInformationService.findById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    DiseaseInformationDTO create (@RequestBody DiseaseInformation diseaseInformation){

        return this.diseaseInformationService.create(diseaseInformation);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    DiseaseInformationDTO update (@RequestBody DiseaseInformationDTO diseaseInformationDTO, @PathVariable(name = "id") Long id) throws Exception{
        return this.diseaseInformationService.update(diseaseInformationDTO, id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    void delete (@PathVariable(name = "id") Long id) throws Exception{
        this.diseaseInformationService.delete(id);
    }

    @GetMapping("findByName/{name}")
    List<DiseaseInformationDTO> findByName (@PathVariable(name = "name") String name) {
        return this.diseaseInformationService.findByName(name);
    }
}
