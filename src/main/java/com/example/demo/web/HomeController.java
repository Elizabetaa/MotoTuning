package com.example.demo.web;

import com.example.demo.service.InquiryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
    public String home(Model model) {
        model.addAttribute("tasks", inquiryService.findTasks());
        return "home";
    }

    @GetMapping("/home/motorcycle")
    public String motorcycle() {
        return "motorcycle_passion";
    }

    @GetMapping("/home/utv")
    public String utv() {
        return "utv_passion";
    }

    @GetMapping("/home/dirtBike")
    public String dirtBike() {
        return "dirt_bike_passion";
    }
    @GetMapping("/home/atv")
    public String atv() {
        return "atv_passion";
    }

}
