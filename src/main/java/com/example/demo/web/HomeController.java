package com.example.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    @GetMapping("/")
    public String index() {
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
