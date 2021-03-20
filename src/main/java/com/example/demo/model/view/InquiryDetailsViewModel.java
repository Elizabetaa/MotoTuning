package com.example.demo.model.view;

import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.entity.enums.BrandsNameEnum;
import com.example.demo.model.entity.enums.InquiryTypeNameEnum;
import com.example.demo.model.entity.enums.ServiceTypeNameEnum;
import com.example.demo.model.entity.enums.VehicleTypeNameEnum;

public class InquiryDetailsViewModel {
    private Long id;
    private InquiryTypeNameEnum inquiry;
    private String email;
    private String phoneNumber;
    private VehicleTypeNameEnum vehicle;
    private ServiceTypeNameEnum service;
    private BrandsNameEnum brand;
    private String model;
    private String description;
    private String response;
    private UserEntity author;

    public InquiryDetailsViewModel() {
    }

    public Long getId() {
        return id;
    }

    public InquiryDetailsViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public InquiryTypeNameEnum getInquiry() {
        return inquiry;
    }

    public InquiryDetailsViewModel setInquiry(InquiryTypeNameEnum inquiry) {
        this.inquiry = inquiry;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public InquiryDetailsViewModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public InquiryDetailsViewModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public VehicleTypeNameEnum getVehicle() {
        return vehicle;
    }

    public InquiryDetailsViewModel setVehicle(VehicleTypeNameEnum vehicle) {
        this.vehicle = vehicle;
        return this;
    }

    public ServiceTypeNameEnum getService() {
        return service;
    }

    public InquiryDetailsViewModel setService(ServiceTypeNameEnum service) {
        this.service = service;
        return this;
    }

    public BrandsNameEnum getBrand() {
        return brand;
    }

    public InquiryDetailsViewModel setBrand(BrandsNameEnum brand) {
        this.brand = brand;
        return this;
    }

    public String getModel() {
        return model;
    }

    public InquiryDetailsViewModel setModel(String model) {
        this.model = model;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public InquiryDetailsViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getResponse() {
        return response;
    }

    public InquiryDetailsViewModel setResponse(String response) {
        this.response = response;
        return this;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public InquiryDetailsViewModel setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }
}
