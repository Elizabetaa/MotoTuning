package com.example.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/service")
public class ServiceInformationController {

    @GetMapping("/information")
    public String getInfo(){
        return "service_information";
    }
    @GetMapping("/information/add")
    public String add(){
        return "service_information_add";
    }
}
