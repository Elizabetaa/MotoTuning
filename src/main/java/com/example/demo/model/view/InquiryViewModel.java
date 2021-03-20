package com.example.demo.model.view;

import com.example.demo.model.entity.enums.VehicleTypeNameEnum;

public class InquiryViewModel {
    private Long id;
    private String email;
    private VehicleTypeNameEnum vehicle;
    private String model;

    public InquiryViewModel() {
    }

    public Long getId() {
        return id;
    }

    public InquiryViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public InquiryViewModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public VehicleTypeNameEnum getVehicle() {
        return vehicle;
    }

    public InquiryViewModel setVehicle(VehicleTypeNameEnum vehicle) {
        this.vehicle = vehicle;
        return this;
    }

    public String getModel() {
        return model;
    }

    public InquiryViewModel setModel(String model) {
        this.model = model;
        return this;
    }
}
