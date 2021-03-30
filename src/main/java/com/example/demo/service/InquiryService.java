package com.example.demo.service;

import com.example.demo.model.binding.InquiryTuningBindingModel;
import com.example.demo.model.entity.InquiryEntity;
import com.example.demo.model.service.AddResponseServiceModel;
import com.example.demo.model.service.InquiryVehicleServiceServiceModel;
import com.example.demo.model.view.InquiryDetailsViewModel;
import com.example.demo.model.view.InquiryTaskViewModel;
import com.example.demo.model.view.InquiryViewModel;
import com.example.demo.model.view.MyInquiriesViewModel;

import java.util.List;

public interface InquiryService {
    List<InquiryEntity> addInquiryVehicleService(InquiryVehicleServiceServiceModel map);

    List<InquiryEntity> addInquiryVehicleTuning(InquiryTuningBindingModel map);

    List<InquiryViewModel> findAllInquiriesForService();

    List<InquiryViewModel> findAllInquiriesForTuning();

    InquiryDetailsViewModel findById(Long id);

    void addRequest(AddResponseServiceModel map);

    List<MyInquiriesViewModel> getMyInquiries(String email);

    void deleteById(Long id);

    void agree(Long id);

    List<InquiryTaskViewModel> findTasks();
}
