package com.example.demo.model.binding;

import com.example.demo.model.entity.enums.MakeNameEnum;
import com.example.demo.model.entity.enums.InquiryTypeNameEnum;
import com.example.demo.model.entity.enums.VehicleTypeNameEnum;

import javax.validation.constraints.*;

public class InquiryTuningBindingModel {
    private String email;
    private String phoneNumber;
    private InquiryTypeNameEnum inquiry;
    private VehicleTypeNameEnum vehicle;
    private MakeNameEnum make;
    private String model;
    private String description;

    public InquiryTuningBindingModel() {
    }


    public String getEmail() {
        return email;
    }

    public InquiryTuningBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    @NotBlank
    @Size(min = 10,message = "Number must be ta least 10 digits")
    @Pattern(regexp = "(\\+\\d{12})|(\\d{10})",message = "Invalid phone number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public InquiryTuningBindingModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public InquiryTypeNameEnum getInquiry() {
        return inquiry;
    }

    public InquiryTuningBindingModel setInquiry(InquiryTypeNameEnum inquiry) {
        this.inquiry = inquiry;
        return this;
    }

    @NotNull(message = "Select vehicle type")
    public VehicleTypeNameEnum getVehicle() {
        return vehicle;
    }

    public InquiryTuningBindingModel setVehicle(VehicleTypeNameEnum vehicle) {
        this.vehicle = vehicle;
        return this;
    }

    public MakeNameEnum getMake() {
        return make;
    }

    public void setMake(MakeNameEnum make) {
        this.make = make;
    }

    @NotNull(message = "Select make")

    @NotBlank(message = "Model can not be empty")
    @Size(min = 2,message = "Invalid model")
    public String getModel() {
        return model;
    }

    public InquiryTuningBindingModel setModel(String model) {
        this.model = model;
        return this;
    }

    @NotBlank(message = "You must write tuning details")
    @Size(min = 10,message = "Description must contain at least 10 symbols")
    public String getDescription() {
        return description;
    }

    public InquiryTuningBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }
}
