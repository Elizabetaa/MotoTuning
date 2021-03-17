package com.example.demo.service;

import com.example.demo.model.binding.InquiryTuningBindingModel;
import com.example.demo.model.service.InquiryVehicleServiceServiceModel;

public interface InquiryService {
    void addInquiryVehicleService(InquiryVehicleServiceServiceModel map);

    void addInquiryVehicleTuning(InquiryTuningBindingModel map);
}
