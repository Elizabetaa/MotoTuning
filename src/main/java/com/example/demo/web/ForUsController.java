package com.example.demo.web;

import com.example.demo.service.CarouselService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tuning")
public class ForUsController {
    private final CarouselService carouselService;

    public ForUsController(CarouselService carouselService) {
        this.carouselService = carouselService;
    }


    @GetMapping("/contacts")
    public String contacts() {
        return "contacts";
    }

    @GetMapping("/aboutUs")
    public String aboutUs(Model model) {
        model.addAttribute("firstImg", carouselService.firstImage());
        model.addAttribute("secondImg", carouselService.secondImage());
        model.addAttribute("thirdImg", carouselService.thirdImage());
        return "about_us";
    }
}
