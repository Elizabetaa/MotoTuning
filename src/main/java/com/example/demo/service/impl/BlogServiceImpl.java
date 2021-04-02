package com.example.demo.service.impl;

import com.example.demo.config.util.ValidationUtil;
import com.example.demo.model.dto.BlogImportDro;
import com.example.demo.model.entity.BlogEntity;
import com.example.demo.model.entity.enums.BlogCategoryNameEnum;
import com.example.demo.model.service.AddBlogServiceModel;
import com.example.demo.model.view.BlogDetailsViewModel;
import com.example.demo.model.view.BlogViewModel;
import com.example.demo.repository.BlogRepository;
import com.example.demo.service.BlogService;
import com.example.demo.service.CloudinaryService;
import com.example.demo.service.UserService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BlogServiceImpl implements BlogService {
    private final ModelMapper modelMapper;
    private final BlogRepository blogRepository;
    private final CloudinaryService cloudinaryService;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    @Autowired
    private  Gson gson;
    @Autowired
    private  ValidationUtil validationUtil;
    private final UserService userService;


    public BlogServiceImpl(ModelMapper modelMapper, BlogRepository blogRepository,
                           CloudinaryService cloudinaryService, UserService userService) {
        this.modelMapper = modelMapper;
        this.blogRepository = blogRepository;
        this.cloudinaryService = cloudinaryService;
        this.userService = userService;
    }



    @Override
    public void addBlog(AddBlogServiceModel addBlogServiceModel) throws IOException {
        addBlogServiceModel.setAddedOn(LocalDateTime.now());
        BlogEntity blogEntity = this.modelMapper.map(addBlogServiceModel, BlogEntity.class);
            MultipartFile img = addBlogServiceModel.getImageUrl();
            String url = cloudinaryService.uploadImage(img);
            blogEntity.setImageUrl(url);
            this.blogRepository.save(blogEntity);
    }

    @Override
    public List<BlogViewModel> findFirstFour() {
        List<BlogViewModel> blogViewModels = new ArrayList<>();
        this.blogRepository.findTop4ByOrderByAddedOnDesc().forEach(b -> {
            BlogViewModel blogViewModel = this.modelMapper.map(b, BlogViewModel.class);
            blogViewModel.setAddedOn(b.getAddedOn().format(formatter));
            blogViewModels.add(blogViewModel);
        });
        return blogViewModels;
    }

    @Override
    public List<BlogViewModel> findByCategory(BlogCategoryNameEnum category) {
        List<BlogViewModel> blogViewModels = new ArrayList<>();
        this.blogRepository.findByBlogCategory(category).forEach(b -> {
            blogViewModels.add(this.modelMapper.map(b,BlogViewModel.class));
        });
        return blogViewModels;
    }

    @Override
    public BlogDetailsViewModel findById(Long id) {
        BlogEntity blogEntity = this.blogRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No value present"));
        BlogDetailsViewModel map = this.modelMapper.map(blogEntity, BlogDetailsViewModel.class);
        map.setAddedOn(blogEntity.getAddedOn().format(formatter));
        return map;
    }
    public String readPassengersFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of("src/main/resources/static/files/blogs.json")));
    }

    @Override
    public void initBlog() throws IOException {
        if (blogRepository.count() == 0) {
            BlogImportDro[] blogImportDros = this.gson.fromJson(readPassengersFileContent(), BlogImportDro[].class);
            for (BlogImportDro importDto : blogImportDros) {
                if (validationUtil.isValid(importDto)) {
                    BlogEntity blogEntity = this.modelMapper.map(importDto, BlogEntity.class);
                    blogEntity.setAuthor(userService.findByEmail("user@mail.com"));
                    this.blogRepository.save(blogEntity);
                }
            }
        }
    }


}
