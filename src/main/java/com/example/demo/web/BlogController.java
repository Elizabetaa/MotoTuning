package com.example.demo.web;

import com.example.demo.model.binding.AddBlogBindingModel;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.entity.enums.BlogCategoryNameEnum;
import com.example.demo.model.service.AddBlogServiceModel;
import com.example.demo.model.view.BlogViewModel;
import com.example.demo.service.BlogService;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogController {
    private final BlogService blogService;
    private final ModelMapper modelMapper;
    private final UserService userService;
    public BlogController(BlogService blogService, ModelMapper modelMapper, UserService userService) {
        this.blogService = blogService;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping("/all")
    public String blog(Model model) {
        List<BlogViewModel> viewModels = this.blogService.findFirstFour();
        model.addAttribute("blogs",this.blogService.findFirstFour());
        return "blog";
    }

    @GetMapping("/add")
    public String addPost(Model model){
        if (!model.containsAttribute("addBlogBindingModel")){
            model.addAttribute("addBlogBindingModel", new AddBlogBindingModel());
            model.addAttribute("userNotFound",false);
        }
        return "blog-add";
    }
    @PostMapping("/add")
    public String addPostConfirm(@Valid AddBlogBindingModel addBlogBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {
        UserEntity byEmail = this.userService.findByEmail(addBlogBindingModel.getEmail());
        if (bindingResult.hasErrors() || byEmail == null){
            redirectAttributes.addFlashAttribute("userNotFound",true);
            redirectAttributes.addFlashAttribute("addBlogBindingModel",addBlogBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addBlogBindingModel",bindingResult);
            return "redirect:add";
        }
        AddBlogServiceModel addBlogServiceModel = this.modelMapper.map(addBlogBindingModel, AddBlogServiceModel.class);
        addBlogServiceModel.setAuthor(byEmail);
        this.blogService.addBlog(addBlogServiceModel);

        return "redirect:all";
    }
    @GetMapping("/categories/road")
    public String postsRoad(Model model){
        model.addAttribute("categoryPage", "ROAD POSTS");
        model.addAttribute("blogs",this.blogService.findByRoad(BlogCategoryNameEnum.ROADS));
        return "by-category";
    }
    @GetMapping("/categories/motorcycle")
    public String postsMotorcycle(Model model){
        model.addAttribute("categoryPage", "MOTORCYCLE POSTS");
        model.addAttribute("blogs",this.blogService.findByRoad(BlogCategoryNameEnum.MOTORCYCLE));
        return "by-category";
    }
    @GetMapping("/categories/tuning")
    public String postsTuning(Model model){
        model.addAttribute("categoryPage", "TUNING POSTS");
        model.addAttribute("blogs",this.blogService.findByRoad(BlogCategoryNameEnum.TUNING));
        return "by-category";
    }
    @GetMapping("/categories/brands")
    public String postsBrands(Model model){
        model.addAttribute("categoryPage", "BRAND POSTS");
        model.addAttribute("blogs",this.blogService.findByRoad(BlogCategoryNameEnum.BRAND));
        return "by-category";
    }
}
