package com.example.demo.model.view;

import com.example.demo.model.entity.CommentEntity;
import com.example.demo.model.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.List;

public class NewsViewModel {
    private Long id;
    private String title;
    private String imageUrl;
    private String addedOn;

    public NewsViewModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(String addedOn) {
        this.addedOn = addedOn;
    }
}
