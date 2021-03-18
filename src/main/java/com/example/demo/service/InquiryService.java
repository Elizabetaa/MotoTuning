package com.example.demo.service;

import com.example.demo.model.binding.InquiryTuningBindingModel;
import com.example.demo.model.entity.InquiryEntity;
import com.example.demo.model.service.AddResponseServiceModel;
import com.example.demo.model.service.InquiryVehicleServiceServiceModel;

import java.util.List;

public interface InquiryService {
    void addInquiryVehicleService(InquiryVehicleServiceServiceModel map);

    void addInquiryVehicleTuning(InquiryTuningBindingModel map);

    List<InquiryEntity> findAllInquiriesForService();

    List<InquiryEntity> findAllInquiriesForTuning();

    InquiryEntity findById(Long id);

    void addRequest(AddResponseServiceModel map);
}
