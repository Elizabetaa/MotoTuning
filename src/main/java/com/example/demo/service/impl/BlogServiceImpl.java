package com.example.demo.service.impl;

import com.example.demo.model.entity.BlogEntity;
import com.example.demo.model.service.AddBlogServiceModel;
import com.example.demo.repository.BlogRepository;
import com.example.demo.service.BlogService;
import com.example.demo.service.CloudinaryService;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class BlogServiceImpl implements BlogService {
    private final ModelMapper modelMapper;
    private final BlogRepository blogRepository;
    private final CloudinaryService cloudinaryService;
    private final UserService userService;

    public BlogServiceImpl(ModelMapper modelMapper, BlogRepository blogRepository, CloudinaryService cloudinaryService, UserService userService) {
        this.modelMapper = modelMapper;
        this.blogRepository = blogRepository;
        this.cloudinaryService = cloudinaryService;
        this.userService = userService;
    }

    @Override
    public void addBlog(AddBlogServiceModel addBlogServiceModel) throws IOException {
        addBlogServiceModel.setAddedOn(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        BlogEntity blogEntity = this.modelMapper.map(addBlogServiceModel, BlogEntity.class);
        if (addBlogServiceModel.getImageUrl().isEmpty()) {
            blogEntity.setImageUrl("https://res.cloudinary.com/elizabetak/image/upload/v1616501806/p5dneoqsob60zdzck4xd.jpg");
            this.blogRepository.save(blogEntity);
        } else {
            MultipartFile img = addBlogServiceModel.getImageUrl();
            String url = cloudinaryService.uploadImage(img);
            blogEntity.setImageUrl(url);
            this.blogRepository.save(blogEntity);
        }
    }
}
