package com.example.demo.web;

import com.example.demo.model.binding.InquiryVehicleServiceBindingModel;
import com.example.demo.model.entity.enums.InquiryTypeNameEnum;
import com.example.demo.model.service.InquiryVehicleServiceServiceModel;
import com.example.demo.service.InquiryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String addOrder(){
        return "makeOrder";
    }

    @GetMapping("/vehicleService")
    public String vehicleServiceOrder(Model model){
        if (!model.containsAttribute("inquiryVehicleServiceBindingModel")){
            model.addAttribute("inquiryVehicleServiceBindingModel", new InquiryVehicleServiceBindingModel());
        }
        return "orderVehicleService";
    }

    @PostMapping("/vehicleService")
    public String vehicleServiceConfirm(@Valid InquiryVehicleServiceBindingModel inquiryVehicleServiceBindingModel,
                                        BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("inquiryVehicleServiceBindingModel",inquiryVehicleServiceBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.inquiryVehicleServiceBindingModel", bindingResult);

            return "redirect:vehicleService";
        }
        inquiryVehicleServiceBindingModel.setInquiry(InquiryTypeNameEnum.SERVICE);
        inquiryService.addInquiryVehicleService(this.modelMapper.map(inquiryVehicleServiceBindingModel, InquiryVehicleServiceServiceModel.class));

        return "redirect:/users/account";
    }

    @GetMapping("/vehicleTuning")
    public String vehicleTuningOrder(){
        return "orderVehicleTuning";
    }
}
