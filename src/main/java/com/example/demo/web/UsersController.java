package com.example.demo.web;

import com.example.demo.model.binding.EditAccountBindingModel;
import com.example.demo.model.binding.UserRegisterBindingModel;
import com.example.demo.model.service.EditAccountServiceModel;
import com.example.demo.model.service.UserRegisterServiceModel;
import com.example.demo.model.view.CurrentUserViewModel;
import com.example.demo.model.view.MyInquiriesViewModel;
import com.example.demo.service.InquiryService;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final InquiryService inquiryService;

    public UsersController(UserService userService, ModelMapper modelMapper, InquiryService inquiryService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.inquiryService = inquiryService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        if (!model.containsAttribute("userRegisterBindingModel")) {
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
            model.addAttribute("userExistsError", false);
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            return "redirect:register";
        }
        if (userService.userNameExists(userRegisterBindingModel.getEmail())) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("userExistsError", true);

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
    public String myAccount(Model model,Principal principal) {
        model.addAttribute("currentUser", this.userService.findByEmail(principal.getName()));
        model.addAttribute("myInquiries",this.inquiryService.getMyInquiries(principal.getName()));
        return "myAccount";
    }

    @PostMapping("/addImg")
    public String add(@ModelAttribute("imageUrl")  MultipartFile imageUrl) throws IOException {

        this.userService.addImage(imageUrl);
        return "home";
    }

    @GetMapping("/editAccount")
    public String editAccount(Model model, Principal principal) throws IOException {

        if (!model.containsAttribute("currentUserViewModel")){
            model.addAttribute("currentUserViewModel", this.userService.findCurrentUser(principal.getName()));
        }
        return "edit-account";
    }
    @PostMapping("/editAccount")
    public String editAccountConfirm(@Valid CurrentUserViewModel currentUserViewModel,
                                     BindingResult bindingResult, RedirectAttributes redirectAttributes, Principal principal) throws IOException {
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("currentUserViewModel", currentUserViewModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.currentUserViewModel", bindingResult);
            return "redirect:editAccount";
        }

        this.userService.editAccount(this.modelMapper.map(currentUserViewModel, EditAccountServiceModel.class),principal.getName());
        return "redirect:account";
    }

}
