package com.example.demo.web;

import com.example.demo.model.view.NewsVieModel;
import com.example.demo.service.NewsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
       List<NewsVieModel> newsVieModel =  this.newsService.findAllNews();
        model.addAttribute("news",this.newsService.findAllNews());
        return "news";
    }
}
