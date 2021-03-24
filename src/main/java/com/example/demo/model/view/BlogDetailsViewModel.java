package com.example.demo.model.view;

import com.example.demo.model.entity.CommentEntity;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.entity.enums.BlogCategoryNameEnum;

import java.util.List;

public class BlogDetailsViewModel {
    private  Long id;
    private String title;
    private BlogCategoryNameEnum blogCategory;
    private String imageUrl;
    private String description;
    private UserEntity author;
    private String addedOn;
//    private List<CommentEntity> comments;

    public BlogDetailsViewModel() {
    }

    public Long getId() {
        return id;
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

    public void setId(Long id) {
        this.id = id;
    }
    //
//    public List<CommentEntity> getComments() {
//        return comments;
//    }
//
//    public void setComments(List<CommentEntity> comments) {
//        this.comments = comments;
//    }
}
