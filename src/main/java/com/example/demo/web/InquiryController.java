package com.example.demo.web;

import com.example.demo.model.binding.AddResponseBindingModel;
import com.example.demo.model.binding.InquiryTuningBindingModel;
import com.example.demo.model.binding.InquiryVehicleServiceBindingModel;
import com.example.demo.model.entity.InquiryEntity;
import com.example.demo.model.entity.enums.InquiryTypeNameEnum;
import com.example.demo.model.service.AddResponseServiceModel;
import com.example.demo.model.service.InquiryVehicleServiceServiceModel;
import com.example.demo.service.InquiryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/inquiries")
public class InquiryController {
    private final ModelMapper modelMapper;
    private final InquiryService inquiryService;


    public InquiryController(ModelMapper modelMapper, InquiryService inquiryService) {
        this.modelMapper = modelMapper;
        this.inquiryService = inquiryService;
    }

    @GetMapping("/add")
    public String addOrder() {
        return "makeOrder";
    }

    @GetMapping("/vehicleService")
    public String vehicleServiceOrder(Model model) {
        if (!model.containsAttribute("inquiryVehicleServiceBindingModel")) {
            model.addAttribute("inquiryVehicleServiceBindingModel", new InquiryVehicleServiceBindingModel());
        }
        return "orderVehicleService";
    }

    @PostMapping("/vehicleService")
    public String vehicleServiceConfirm(@Valid InquiryVehicleServiceBindingModel inquiryVehicleServiceBindingModel,
                                        BindingResult bindingResult, RedirectAttributes redirectAttributes,Principal principal) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("inquiryVehicleServiceBindingModel", inquiryVehicleServiceBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.inquiryVehicleServiceBindingModel", bindingResult);

            return "redirect:vehicleService";
        }
        inquiryVehicleServiceBindingModel.setInquiry(InquiryTypeNameEnum.SERVICE);
        inquiryVehicleServiceBindingModel.setEmail(principal.getName());
        inquiryService.addInquiryVehicleService(this.modelMapper.map(inquiryVehicleServiceBindingModel, InquiryVehicleServiceServiceModel.class));

        return "redirect:/users/account";
    }

    @GetMapping("/vehicleTuning")
    public String vehicleTuningOrder(Model model) {
        if (!model.containsAttribute("inquiryTuningBindingModel")) {
            model.addAttribute("inquiryTuningBindingModel", new InquiryTuningBindingModel());
        }
        return "orderVehicleTuning";
    }

    @PostMapping("/vehicleTuning")
    public String vehicleTuningConfirm(@Valid InquiryTuningBindingModel inquiryTuningBindingModel,
                                       BindingResult bindingResult,
                                       RedirectAttributes redirectAttributes,Principal principal) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("inquiryTuningBindingModel", inquiryTuningBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.inquiryTuningBindingModel", bindingResult);

            return "redirect:vehicleTuning";
        }
        inquiryTuningBindingModel.setInquiry(InquiryTypeNameEnum.TUNING);
        inquiryTuningBindingModel.setEmail(principal.getName());
        inquiryService.addInquiryVehicleTuning(this.modelMapper.map(inquiryTuningBindingModel,InquiryTuningBindingModel.class));
        return "redirect:/users/account";
    }


    @GetMapping("/details/{id}")
    public String details(Model model, @PathVariable Long id){
        if (!model.containsAttribute("addResponseBindingModel")){
            model.addAttribute("addResponseBindingModel", new AddResponseBindingModel());
        }
        model.addAttribute("inquiryEntity",this.inquiryService.findById(id));
        return "inquiryDetails";
    }

    @PostMapping("/details/{id}")
    public String addResponse(@Valid AddResponseBindingModel addResponseBindingModel,BindingResult bindingResult, RedirectAttributes redirectAttributes, @PathVariable Long id){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("addResponseBindingModel",addResponseBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addResponseBindingModel",bindingResult);
            return "redirect:{id}";
        }
        this.inquiryService.addRequest(this.modelMapper.map(addResponseBindingModel, AddResponseServiceModel.class));
        return "redirect:/admin/actions";
    }

    @GetMapping("/delete/{id}")
    public String deleteInquiry(@PathVariable Long id){
        this.inquiryService.deleteById(id);
        return "redirect:/users/account";
    }
    @GetMapping("/finish/{id}")
    public String finishedTask(@PathVariable Long id){
        this.inquiryService.deleteById(id);
        return "redirect:/home";
    }
    @GetMapping("/agree/{id}")
    public String agreeInquiry(@PathVariable Long id){
        this.inquiryService.agree(id);
        return "redirect:/users/account";
    }
}
