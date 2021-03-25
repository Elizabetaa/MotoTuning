package com.example.demo.model.service;

import org.springframework.web.multipart.MultipartFile;

public class EditAccountServiceModel {
    private String firstName;
    private String lastName;
    private MultipartFile imageUrl;

    public EditAccountServiceModel() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public MultipartFile getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(MultipartFile imageUrl) {
        this.imageUrl = imageUrl;
    }
}
