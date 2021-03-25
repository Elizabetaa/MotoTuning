package com.example.demo.model.binding;

import com.example.demo.config.validator.MultipartFileExists;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.entity.enums.BlogCategoryNameEnum;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class AddBlogBindingModel {
    private String email;
    private String title;
    private BlogCategoryNameEnum blogCategory;
    private MultipartFile imageUrl;
    private String description;

    public AddBlogBindingModel() {
    }

    @NotBlank(message = "Must write email")
    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @MultipartFileExists(message = "Select Image")
    public MultipartFile getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(MultipartFile imageUrl) {
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
}
