package com.example.demo.web;

import com.example.demo.model.entity.InquiryEntity;
import com.example.demo.service.InquiryService;
import com.example.demo.service.impl.InquiryServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final InquiryService inquiryService;

    public AdminController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }


    @GetMapping("/actions")
    public String adminActions(Model model){
        model.addAttribute("service", inquiryService.findAllInquiriesForService());
        model.addAttribute("tuning", inquiryService.findAllInquiriesForTuning());
        return "adminActions";
    }


}
