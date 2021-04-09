package com.example.demo.web;

import com.example.demo.service.CommentService;
import com.example.demo.service.NewsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/news")
public class NewsController {
    private final NewsService newsService;
    private final CommentService commentService;
    public NewsController(NewsService newsService, CommentService commentService) {
        this.newsService = newsService;
        this.commentService = commentService;
    }

    @GetMapping("/all")
    public String all(Model model) {
        model.addAttribute("news",this.newsService.findAllNews());
        return "news";
    }

    @GetMapping("/details/{id}")
    public String detailsNews (@PathVariable Long id, Model model){
        model.addAttribute("news", this.newsService.findById(id));
        return "news_details";
    }
    @PostMapping("/details/comments/add/{id}")
    public String addComment(@PathVariable Long id, @RequestParam("comment") String comment, Principal principal){
        if (!comment.trim().equals("")) {
            this.commentService.addCommentToNews(comment, principal.getName(), id);
        }
        return "redirect:/news/details/{id}";
    }
}
