package com.example.demo;

import com.example.demo.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {
    private final UserService userService;
    private final RoleService roleService;
    private final NewsService newsService;
    private final BlogService blogService;
    private final MotorcyclesInformationService motorcyclesInformationService;

    public DBInit(UserService userService, RoleService roleService, NewsService newsService, BlogService blogService, MotorcyclesInformationService motorcyclesInformationService) {
        this.userService = userService;
        this.roleService = roleService;
        this.newsService = newsService;
        this.blogService = blogService;
        this.motorcyclesInformationService = motorcyclesInformationService;
    }

    @Override
    public void run(String... args) throws Exception {
        //ToDo for testing comment these methods
        roleService.initRoles();
        newsService.initNews();
        userService.initUsers();
        blogService.initBlog();
        motorcyclesInformationService.initInfo();


    }
}
