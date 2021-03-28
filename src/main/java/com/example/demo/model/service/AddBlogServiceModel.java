package com.example.demo.model.service;

import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.entity.enums.BlogCategoryNameEnum;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

public class AddBlogServiceModel {
    private String title;
    private BlogCategoryNameEnum blogCategory;
    private MultipartFile imageUrl;
    private String description;
    private UserEntity author;
    private LocalDateTime addedOn;

    public AddBlogServiceModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BlogCategoryNameEnum getBlogCategory() {
        return blogCategory;
    }

    public void setBlogCategory(BlogCategoryNameEnum blogCategory) {
        this.blogCategory = blogCategory;
    }

    public MultipartFile getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(MultipartFile imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
    }
}
