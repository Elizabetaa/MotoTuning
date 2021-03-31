package com.example.demo.model.view;

import com.example.demo.model.entity.enums.MakeNameEnum;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.Year;

public class ServiceInfoViewModel {
    private MakeNameEnum make;
    private String model;
    private Year year;
    private MultipartFile pdfUrl;
    private LocalDateTime addedOn;

    public ServiceInfoViewModel() {
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

    public MultipartFile getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(MultipartFile pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
    }
}
