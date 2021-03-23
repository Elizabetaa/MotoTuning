package com.example.demo.model.view;

import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.entity.enums.BlogCategoryNameEnum;

public class BlogDetailsViewModel {
    private String title;
    private BlogCategoryNameEnum blogCategory;
    private String imageUrl;
    private String description;
    private UserEntity author;
    private String addedOn;

    public BlogDetailsViewModel() {
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
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

    public String getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(String addedOn) {
        this.addedOn = addedOn;
    }
}
