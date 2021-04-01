package com.example.demo.web;

import com.example.demo.model.binding.ServiceInformationBindingModel;
import com.example.demo.model.service.ServiceInformationServiceModel;
import com.example.demo.model.view.ServiceInfoViewModel;
import com.example.demo.model.view.ServiceInformationDetailsViewModel;
import com.example.demo.service.MotorcyclesInformationService;
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
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/service")
public class ServiceInformationController {
    private final ModelMapper modelMapper;
    private final MotorcyclesInformationService motorcyclesInformationService;

    public ServiceInformationController(ModelMapper modelMapper, MotorcyclesInformationService motorcyclesInformationService) {
        this.modelMapper = modelMapper;
        this.motorcyclesInformationService = motorcyclesInformationService;
    }

    @GetMapping("/information")
    public String getInfo(Model model){
        List<ServiceInfoViewModel> all = motorcyclesInformationService.getAll();
        model.addAttribute("manuals",motorcyclesInformationService.getAll());
        return "service_information";
    }
    @GetMapping("/information/add")
    public String add(Model model){
        if (!model.containsAttribute("serviceInformationBindingModel")){
            model.addAttribute("serviceInformationBindingModel",new ServiceInformationBindingModel());
        }
        return "service_information_add";
    }
    @PostMapping("/information/add")
    public String addConfirm(@Valid ServiceInformationBindingModel serviceInformationBindingModel,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException, SQLException {

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("serviceInformationBindingModel", serviceInformationBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.serviceInformationBindingModel",bindingResult);
            return "redirect:add";
        }

        ServiceInformationServiceModel serviceInformationServiceModel = this.modelMapper.map(serviceInformationBindingModel, ServiceInformationServiceModel.class);
        serviceInformationServiceModel.setAddedOn(LocalDateTime.now());
        motorcyclesInformationService.addManual(serviceInformationServiceModel);

        return "redirect:/service/information";
    }

    @GetMapping("/details/{id}")
    private String getInfoDetails(Model model, @PathVariable Long id){
        ServiceInformationDetailsViewModel serviceInformationDetailsViewModel = this.motorcyclesInformationService.getById(id);
        model.addAttribute("info",this.motorcyclesInformationService.getById(id));
        return "information_details";
    }

}
