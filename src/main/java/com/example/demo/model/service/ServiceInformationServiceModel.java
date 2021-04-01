package com.example.demo.model.service;

import com.example.demo.model.entity.enums.MakeNameEnum;

import java.time.LocalDateTime;
import java.time.Year;

public class ServiceInformationServiceModel {
    private MakeNameEnum make;
    private String model;
    private Year year;
    private String pdfUrl;
    private LocalDateTime addedOn;

    public ServiceInformationServiceModel() {
    }

    public MakeNameEnum getMake() {
        return make;
    }

    public void setMake(MakeNameEnum make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

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
