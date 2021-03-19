package com.example.demo.service.impl;

import com.example.demo.model.binding.InquiryTuningBindingModel;
import com.example.demo.model.entity.InquiryEntity;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.entity.enums.InquiryTypeNameEnum;
import com.example.demo.model.service.AddResponseServiceModel;
import com.example.demo.model.service.InquiryVehicleServiceServiceModel;
import com.example.demo.repository.InquiryRepository;
import com.example.demo.service.InquiryService;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @CacheEvict(value = "inquiriesService", allEntries = true)
    public List<InquiryEntity> addInquiryVehicleService(InquiryVehicleServiceServiceModel inquiry) {
        InquiryEntity inquiryEntity = this.modelMapper.map(inquiry, InquiryEntity.class);
        String authentication = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity byEmail = userService.findByEmail(authentication);
        inquiryEntity.setAuthor(byEmail);
        this.inquiryRepository.save(inquiryEntity);
        return this.inquiryRepository.findByInquiryAndResponse(InquiryTypeNameEnum.SERVICE,null).orElse(null);
    }

    @Override
    @CacheEvict(value = "inquiriesTuning", allEntries = true)
    public List<InquiryEntity> addInquiryVehicleTuning(InquiryTuningBindingModel map) {
        InquiryEntity inquiryEntity = this.modelMapper.map(map,InquiryEntity.class);
        String authentication = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity byEmail = userService.findByEmail(authentication);
        inquiryEntity.setAuthor(byEmail);
        this.inquiryRepository.save(inquiryEntity);
        return this.inquiryRepository.findByInquiryAndResponse(InquiryTypeNameEnum.SERVICE,null).orElse(null);
    }

    @Override
    @Cacheable("inquiriesService")
    public List<InquiryEntity> findAllInquiriesForService() {
        //TODO make InquiryServiceViewModel

        return this.inquiryRepository.findByInquiryAndResponse(InquiryTypeNameEnum.SERVICE,null).orElse(null);
    }

    @Override
    @Cacheable("inquiriesTuning")
    public List<InquiryEntity> findAllInquiriesForTuning() {
        //TODO make InquiryServiceViewModel
        return this.inquiryRepository.findByInquiryAndResponse(InquiryTypeNameEnum.TUNING,null).orElse(null);
    }

    @Override
    public InquiryEntity findById(Long id) {
        return this.inquiryRepository.findById(id).orElse(null);
    }

    @Override
    public void addRequest(AddResponseServiceModel map) {
        InquiryEntity inquiryEntity = this.inquiryRepository.findById(map.getId()).orElse(null);
        inquiryEntity.setResponse(map.getResponse());
        this.inquiryRepository.save(inquiryEntity);
    }


}
