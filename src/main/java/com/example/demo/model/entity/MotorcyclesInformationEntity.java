package com.example.demo.model.entity;

import com.example.demo.model.entity.enums.MakeNameEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.time.Year;

@Entity
@Table(name = "motorcycles_information")
public class MotorcyclesInformationEntity extends BaseEntity{
    private MakeNameEnum make;
    private String model;
    private Year year;
    private String pdfUrl;
    private LocalDateTime addedOn;


    public MotorcyclesInformationEntity() {
    }

    @Column(nullable = false)
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

    @Column(nullable = false)
    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    @Column(columnDefinition = "TEXT",nullable = false)
    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String description) {
        this.pdfUrl = description;
    }



    @Column(nullable = false)
    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
    }
}
