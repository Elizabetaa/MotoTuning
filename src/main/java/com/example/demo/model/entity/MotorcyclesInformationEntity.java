package com.example.demo.model.entity;

import com.example.demo.model.entity.enums.MakeNameEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.sql.Blob;
import java.time.LocalDateTime;
import java.time.Year;

@Entity
@Table(name = "motorcycles_information")
public class MotorcyclesInformationEntity extends BaseEntity{
    private MakeNameEnum make;
    private String model;
    private Year year;
    private String description;
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
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    @Column(nullable = false)
    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
    }
}
