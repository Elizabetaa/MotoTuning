package com.example.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsersController {


    @GetMapping("/users/register")
    public String home(){
        return "register";
    }

    @GetMapping("/users/signIn")
    public String signIn(){
        return "signIn";
    }
}
