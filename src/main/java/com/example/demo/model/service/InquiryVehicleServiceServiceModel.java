package com.example.demo.model.service;

import com.example.demo.model.entity.enums.MakeNameEnum;
import com.example.demo.model.entity.enums.InquiryTypeNameEnum;
import com.example.demo.model.entity.enums.ServiceTypeNameEnum;
import com.example.demo.model.entity.enums.VehicleTypeNameEnum;

public class InquiryVehicleServiceServiceModel {
    private String email;
    private String phoneNumber;
    private InquiryTypeNameEnum inquiry;
    private VehicleTypeNameEnum vehicle;
    private ServiceTypeNameEnum vehicleService;
    private MakeNameEnum make;
    private String model;
    private String description;

    public InquiryVehicleServiceServiceModel() {
    }

    public String getEmail() {
        return email;
    }

    public InquiryVehicleServiceServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public InquiryTypeNameEnum getInquiry() {
        return inquiry;
    }

    public InquiryVehicleServiceServiceModel setInquiry(InquiryTypeNameEnum inquiry) {
        this.inquiry = inquiry;
        return this;
    }

    public InquiryVehicleServiceServiceModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public VehicleTypeNameEnum getVehicle() {
        return vehicle;
    }

    public InquiryVehicleServiceServiceModel setVehicle(VehicleTypeNameEnum vehicle) {
        this.vehicle = vehicle;
        return this;
    }

    public ServiceTypeNameEnum getVehicleService() {
        return vehicleService;
    }

    public InquiryVehicleServiceServiceModel setVehicleService(ServiceTypeNameEnum vehicleService) {
        this.vehicleService = vehicleService;
        return this;
    }

    public MakeNameEnum getMake() {
        return make;
    }

    public InquiryVehicleServiceServiceModel setMake(MakeNameEnum make) {
        this.make = make;
        return this;
    }

    public String getModel() {
        return model;
    }

    public InquiryVehicleServiceServiceModel setModel(String model) {
        this.model = model;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public InquiryVehicleServiceServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }
}
