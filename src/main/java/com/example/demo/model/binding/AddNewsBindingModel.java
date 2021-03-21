package com.example.demo.model.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AddNewsBindingModel {
    private String title;
    private String imageUrl;
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

    @NotBlank(message = "You must select image")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Size(min = 10, message = "Description must be more than 10 symbols")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
