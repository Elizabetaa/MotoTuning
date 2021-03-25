package com.example.demo.model.binding;

import com.example.demo.config.validator.MultipartFileExists;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddNewsBindingModel {
    private String title;
    private MultipartFile imageUrl;
    private String description;

    public AddNewsBindingModel() {
    }

    @NotBlank
    @Size(min = 3, message = "Title must be more than 3 symbols")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @MultipartFileExists(message = "Select Image")
    public MultipartFile getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(MultipartFile imageUrl) {
        this.imageUrl = imageUrl;
    }


    @Size(min = 100, message = "Description must be more than 100 symbols")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
