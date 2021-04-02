package com.example.demo.model.view;

import com.example.demo.model.entity.enums.MakeNameEnum;

import java.time.Year;

public class ServiceInformationDetailsViewModel {
    private Long id;
    private MakeNameEnum make;
    private String model;
    private Year year;
    private String addedOn;
    private String pdfUrl;

    public ServiceInformationDetailsViewModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(String addedOn) {
        this.addedOn = addedOn;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }
}
