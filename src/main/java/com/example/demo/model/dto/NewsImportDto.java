package com.example.demo.model.dto;
import com.example.demo.config.validator.MultipartFileExists;
import com.google.gson.annotations.Expose;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class NewsImportDto {
    @Expose
    private String title;
    @Expose
    private String imageUrl;
    @Expose
    private String description;
    @Expose
    private LocalDateTime addedOn;


    public NewsImportDto() {
    }

    @NotBlank
    @Size(min = 3, message = "Title must be more than 3 symbols")
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


    @Size(min = 100, message = "Description must be more than 100 symbols")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
    }
}
