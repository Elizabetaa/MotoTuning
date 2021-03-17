package com.example.demo.model.binding;

import com.example.demo.model.entity.enums.BrandsNameEnum;
import com.example.demo.model.entity.enums.InquiryTypeNameEnum;
import com.example.demo.model.entity.enums.ServiceTypeNameEnum;
import com.example.demo.model.entity.enums.VehicleTypeNameEnum;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class InquiryVehicleServiceBindingModel {
    private String email;
    private String phoneNumber;
    private InquiryTypeNameEnum inquiry;
    private VehicleTypeNameEnum vehicle;
    private ServiceTypeNameEnum vehicleService;
    private BrandsNameEnum brand;
    private String model;
    private String description;


    public InquiryVehicleServiceBindingModel() {
    }


    @NotBlank
    @Email
    public String getEmail() {
        return email;
    }

    public InquiryVehicleServiceBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    @NotBlank
    @Size(min = 10,max = 10,message = "Invalid phone number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public InquiryVehicleServiceBindingModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    @NotNull(message = "Select vehicle  type")
    public VehicleTypeNameEnum getVehicle() {
        return vehicle;
    }

    public InquiryVehicleServiceBindingModel setVehicle(VehicleTypeNameEnum vehicle) {
        this.vehicle = vehicle;
        return this;
    }

    @NotNull(message = "Select service  type")
    public ServiceTypeNameEnum getVehicleService() {
        return vehicleService;
    }

    public InquiryVehicleServiceBindingModel setVehicleService(ServiceTypeNameEnum vehicleService) {
        this.vehicleService = vehicleService;
        return this;
    }

    @NotNull(message = "Select brand")
    public BrandsNameEnum getBrand() {
        return brand;
    }

    public InquiryVehicleServiceBindingModel setBrand(BrandsNameEnum brand) {
        this.brand = brand;
        return this;
    }

    @NotBlank()
    @Size(min = 3,message = "Invalid model")
    public String getModel() {
        return model;
    }

    public InquiryVehicleServiceBindingModel setModel(String model) {
        this.model = model;
        return this;
    }

    public InquiryTypeNameEnum getInquiry() {
        return inquiry;
    }

    public InquiryVehicleServiceBindingModel setInquiry(InquiryTypeNameEnum inquiry) {
        this.inquiry = inquiry;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public InquiryVehicleServiceBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }
}
