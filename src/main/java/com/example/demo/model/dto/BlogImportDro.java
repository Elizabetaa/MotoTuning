package com.example.demo.model.dto;

import com.example.demo.config.validator.MultipartFileExists;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.entity.enums.BlogCategoryNameEnum;
import com.google.gson.annotations.Expose;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class BlogImportDro {
    @Expose
    private String title;
    @Expose
    private BlogCategoryNameEnum blogCategory;
    @Expose
    private String imageUrl;
    @Expose
    private String description;

    @Expose
    private LocalDateTime addedOn;

    public BlogImportDro() {
    }


    @NotBlank
    @Size(min = 3,message = "Title must contain minimum 3 symbols")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NotNull(message = "Select category")
    public BlogCategoryNameEnum getBlogCategory() {
        return blogCategory;
    }

    public void setBlogCategory(BlogCategoryNameEnum blogCategory) {
        this.blogCategory = blogCategory;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    @NotBlank
    @Size(min = 100,message = "Title must contain minimum 100 symbols")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
    }
}
