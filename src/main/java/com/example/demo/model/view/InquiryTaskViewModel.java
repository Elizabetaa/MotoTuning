package com.example.demo.model.view;

import com.example.demo.model.entity.enums.MakeNameEnum;
import com.example.demo.model.entity.enums.InquiryTypeNameEnum;
import com.example.demo.model.entity.enums.ServiceTypeNameEnum;
import com.example.demo.model.entity.enums.VehicleTypeNameEnum;

public class InquiryTaskViewModel {
    private Long id;
    private InquiryTypeNameEnum inquiry;
    private String email;
    private String phoneNumber;
    private VehicleTypeNameEnum vehicle;
    private ServiceTypeNameEnum service;
    private MakeNameEnum make;
    private String model;
    private String description;
    private String response;


    public InquiryTaskViewModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InquiryTypeNameEnum getInquiry() {
        return inquiry;
    }

    public void setInquiry(InquiryTypeNameEnum inquiry) {
        this.inquiry = inquiry;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public VehicleTypeNameEnum getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleTypeNameEnum vehicle) {
        this.vehicle = vehicle;
    }

    public ServiceTypeNameEnum getService() {
        return service;
    }

    public void setService(ServiceTypeNameEnum service) {
        this.service = service;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
