package com.example.demo.service;

import com.example.demo.model.binding.InquiryTuningBindingModel;
import com.example.demo.model.entity.InquiryEntity;
import com.example.demo.model.service.AddResponseServiceModel;
import com.example.demo.model.service.InquiryVehicleServiceServiceModel;

import java.util.List;

public interface InquiryService {
    List<InquiryEntity> addInquiryVehicleService(InquiryVehicleServiceServiceModel map);

    List<InquiryEntity> addInquiryVehicleTuning(InquiryTuningBindingModel map);

    List<InquiryEntity> findAllInquiriesForService();

    List<InquiryEntity> findAllInquiriesForTuning();

    InquiryEntity findById(Long id);

    void addRequest(AddResponseServiceModel map);
}
