package com.example.demo.service.impl;

import com.example.demo.model.entity.InquiryEntity;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.service.InquiryVehicleServiceServiceModel;
import com.example.demo.repository.InquiryRepository;
import com.example.demo.service.InquiryService;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class InquiryServiceImpl implements InquiryService {
    private final InquiryRepository inquiryRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;


    public InquiryServiceImpl(InquiryRepository inquiryRepository, ModelMapper modelMapper, UserService userService) {
        this.inquiryRepository = inquiryRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public void addInquiryVehicleService(InquiryVehicleServiceServiceModel inquiry) {
        InquiryEntity inquiryEntity = this.modelMapper.map(inquiry, InquiryEntity.class);
        String authentication = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity byEmail = userService.findByEmail(authentication);
        inquiryEntity.setAuthor(byEmail);
        this.inquiryRepository.save(inquiryEntity);
    }
}
