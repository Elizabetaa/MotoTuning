package com.example.demo.model.view;

import com.example.demo.model.entity.enums.VehicleTypeNameEnum;

public class MyInquiriesViewModel {
    private Long id;
    private VehicleTypeNameEnum vehicle;
    private String model;
    private String response;

    public MyInquiriesViewModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VehicleTypeNameEnum getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleTypeNameEnum vehicle) {
        this.vehicle = vehicle;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
