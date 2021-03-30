package com.example.demo.web;

import com.example.demo.model.view.InquiryTaskViewModel;
import com.example.demo.service.InquiryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final InquiryService inquiryService;

    public HomeController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("tasks", inquiryService.findTasks());
        return "home";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/home/motorcycle")
    public String motorcycle() {
        return "motorcyclePassion";
    }

    @GetMapping("/home/utv")
    public String utv() {
        return "utvPassion";
    }

    @GetMapping("/home/dirtBike")
    public String dirtBike() {
        return "dirtBikePassion";
    }
    @GetMapping("/home/atv")
    public String atv() {
        return "atvPassion";
    }

}
