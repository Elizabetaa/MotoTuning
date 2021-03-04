package com.example.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tuning")
public class ForUsController {

    @GetMapping("/contacts")
    public String contacts() {
        return "contacts";
    }
    @GetMapping("/aboutUs")
    public String aboutUs() {
        return "aboutUs";
    }
}
