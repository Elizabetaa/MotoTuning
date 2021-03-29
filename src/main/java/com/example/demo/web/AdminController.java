package com.example.demo.web;

import com.example.demo.model.binding.AddNewsBindingModel;
import com.example.demo.model.service.AddNewsServiceModel;
import com.example.demo.service.InquiryService;
import com.example.demo.service.NewsService;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final InquiryService inquiryService;
    private final ModelMapper modelMapper;
    private final NewsService newsService;
    private final UserService userService;

    public AdminController(InquiryService inquiryService, ModelMapper modelMapper, NewsService newsService, UserService userService) {
        this.inquiryService = inquiryService;
        this.modelMapper = modelMapper;
        this.newsService = newsService;
        this.userService = userService;
    }


    @GetMapping("/actions")
    public String adminActions(Model model, Principal principal) {
        if (!model.containsAttribute("addNewsBindingModel")) {
            model.addAttribute("addNewsBindingModel", new AddNewsBindingModel());
        }
        model.addAttribute("service", inquiryService.findAllInquiriesForService());
        if (inquiryService.findAllInquiriesForService().size() == 0) {
            model.addAttribute("emptyServiceInquiries", true);
        }
        model.addAttribute("tuning", inquiryService.findAllInquiriesForTuning());
        if (inquiryService.findAllInquiriesForTuning().size() == 0) {
            model.addAttribute("emptyTuningInquiries", true);
        }

        model.addAttribute("allUsersEmails",userService.getAllEmails(principal.getName()));

        return "adminActions";
    }


    @PostMapping("/addNews")
    public String addNews(@Valid AddNewsBindingModel addNewsBindingModel,
                          BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addNewsBindingModel", addNewsBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addNewsBindingModel", bindingResult);

            return "redirect:actions#my-form1";
        }
        AddNewsServiceModel addNewsServiceModel = this.modelMapper.map(addNewsBindingModel, AddNewsServiceModel.class);
        addNewsServiceModel.setAddedOn(LocalDateTime.now());


        this.newsService.addNews(addNewsServiceModel);

        return "redirect:/news/all";
    }

    @PostMapping("/role/add")
    public String addRoleToUser(@RequestParam String email,
                                @RequestParam String role){

        this.userService.changeRole(email,role);
        return "redirect:/admin/actions";
    }

}
