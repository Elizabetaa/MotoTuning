package com.example.demo.model.entity;

import com.example.demo.model.entity.enums.InquiryTypeNameEnum;
import com.example.demo.model.entity.enums.ServiceTypeNameEnum;
import com.example.demo.model.entity.enums.VehicleTypeNameEnum;

import javax.persistence.*;

@Entity
@Table(name = "inquiries")
public class InquiryEntity extends BaseEntity{

    private InquiryTypeNameEnum inquiry;
    private String email;
    private String phoneNumber;
    private VehicleTypeNameEnum vehicle;
    private ServiceTypeNameEnum service;
    private String model;
    private String description;
    private String response;
    private UserEntity author;

    public InquiryEntity() {
    }


    @Enumerated(value = EnumType.STRING)
    public InquiryTypeNameEnum getInquiry() {
        return inquiry;
    }

    public InquiryEntity setInquiry(InquiryTypeNameEnum inquiry) {
        this.inquiry = inquiry;
        return this;
    }


    @Column(name = "email",nullable = false)
    public String getEmail() {
        return email;
    }

    public InquiryEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    @Column(nullable = false)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public InquiryEntity setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    @Enumerated(value = EnumType.STRING)
    public VehicleTypeNameEnum getVehicle() {
        return vehicle;
    }

    public InquiryEntity setVehicle(VehicleTypeNameEnum vehicle) {
        this.vehicle = vehicle;
        return this;
    }

    @Enumerated(value = EnumType.STRING)
    public ServiceTypeNameEnum getService() {
        return service;
    }

    public InquiryEntity setService(ServiceTypeNameEnum service) {
        this.service = service;
        return this;
    }

    @Column(nullable = false)
    public String getModel() {
        return model;
    }

    public InquiryEntity setModel(String model) {
        this.model = model;
        return this;
    }

    @Column(columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public InquiryEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    @Column(columnDefinition = "TEXT")
    public String getResponse() {
        return response;
    }

    public InquiryEntity setResponse(String response) {
        this.response = response;
        return this;
    }

    @ManyToOne
    public UserEntity getAuthor() {
        return author;
    }

    public InquiryEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }
}
