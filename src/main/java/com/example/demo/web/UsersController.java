package com.example.demo.web;

import com.example.demo.model.binding.UserRegisterBindingModel;
import com.example.demo.model.service.UserRegisterServiceModel;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    public UsersController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public String register (Model model){
        if (!model.containsAttribute("userRegisterBindingModel")){
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userRegisterBindingModel",userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel",bindingResult);
            return "redirect:register";
        }

        this.userService.register(this.modelMapper.map(userRegisterBindingModel, UserRegisterServiceModel.class));

        return "redirect:signIn";

    }


    @GetMapping("/signIn")
    public String signIn() {
        return "signIn";
    }

    @PostMapping("/signIn-error")
    public ModelAndView failedLogin(@ModelAttribute("email")
                                            String email,
                                    RedirectAttributes attributes) {

        ModelAndView modelAndView = new ModelAndView();

        attributes.addFlashAttribute("failure", true);
        attributes.addFlashAttribute("email", email);

        modelAndView.setViewName("redirect:/users/signIn");

        return modelAndView;
    }

    @GetMapping("/account")
    public String myAccount(){
        return "myAccount";
    }

}
