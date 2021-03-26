package com.example.demo.model.view;

import com.example.demo.config.validator.MultipartFileExists;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CurrentUserViewModel {
    public String email;
    public String firstName;
    public String lastName;
    public MultipartFile imageUrl;

    public CurrentUserViewModel() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @NotBlank
    @Size(min = 2, message = "First name length must be at least 2 symbols")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @NotBlank
    @Size(min = 2, message = "Last name length must be at least 2 symbols")
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
