package com.example.demo.service;

import com.example.demo.model.service.AddBlogServiceModel;

import java.io.IOException;

public interface BlogService {
    void addBlog(AddBlogServiceModel map) throws IOException;
}
