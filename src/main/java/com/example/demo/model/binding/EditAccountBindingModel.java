package com.example.demo.model.binding;


import com.example.demo.config.validator.MultipartFileExists;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class EditAccountBindingModel {
    private String firstName;
    private String lastName;

    private MultipartFile imageUrl;

    public EditAccountBindingModel() {
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
