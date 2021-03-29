package com.example.demo.web;

import com.example.demo.model.view.NewsViewModel;
import com.example.demo.service.NewsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/news")
public class NewsController {
    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/all")
    public String all(Model model) {
        model.addAttribute("news",this.newsService.findAllNews());
        return "news";
    }

    @GetMapping("details/{id}")
    public String detailsNews (@PathVariable Long id, Model model){
        model.addAttribute("news", this.newsService.findById(id));
        return "news-details";
    }
}
