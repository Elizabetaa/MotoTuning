package com.example.demo.model.dto;

import com.example.demo.model.entity.enums.MakeNameEnum;
import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.Year;

public class InformationDto {
    @Expose
    private MakeNameEnum make;
    @Expose
    private String model;
    @Expose
    private Year year;
    @Expose
    private String pdfUrl;
    @Expose
    private LocalDateTime addedOn;

    public InformationDto() {
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
    @Pattern(regexp = "^(https:|http:)(?!.*\\.pdf).*$",message = "Invalid PDF link")
    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
    }
}
