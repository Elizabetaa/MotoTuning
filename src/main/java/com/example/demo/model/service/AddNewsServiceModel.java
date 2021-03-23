package com.example.demo.model.service;

import com.example.demo.model.entity.UserEntity;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

public class AddNewsServiceModel {
    private String title;
    private MultipartFile imageUrl;
    private String description;
    private String addedOn;

    public AddNewsServiceModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(String addedOn) {
        this.addedOn = addedOn;
    }
}
