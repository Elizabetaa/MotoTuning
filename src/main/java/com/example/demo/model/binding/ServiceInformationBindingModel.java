package com.example.demo.model.binding;

import com.example.demo.config.validator.MultipartFileExists;
import com.example.demo.model.entity.enums.MakeNameEnum;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.Year;

public class ServiceInformationBindingModel {
    private MakeNameEnum make;
    private String model;
    private Year year;
    private String description;

    public ServiceInformationBindingModel() {
    }

    @NotNull(message = "Select make")
    public MakeNameEnum getMake() {
        return make;
    }

    public void setMake(MakeNameEnum make) {
        this.make = make;
    }

    @NotBlank
    @Size(min = 2,message = "Model should be at least 2 symbols")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @NotNull(message = "Select year")
    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }


    @NotBlank
    @Size(min = 100,message = "Description should be at least 100 symbols")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
