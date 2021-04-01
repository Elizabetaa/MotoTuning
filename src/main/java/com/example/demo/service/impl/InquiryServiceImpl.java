package com.example.demo.service.impl;

import com.example.demo.model.binding.InquiryTuningBindingModel;
import com.example.demo.model.entity.InquiryEntity;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.entity.enums.InquiryTypeNameEnum;
import com.example.demo.model.service.AddResponseServiceModel;
import com.example.demo.model.service.InquiryVehicleServiceServiceModel;
import com.example.demo.model.view.InquiryDetailsViewModel;
import com.example.demo.model.view.InquiryTaskViewModel;
import com.example.demo.model.view.InquiryViewModel;
import com.example.demo.model.view.MyInquiriesViewModel;
import com.example.demo.repository.InquiryRepository;
import com.example.demo.service.InquiryService;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        mapInquiry(inquiryEntity);
        this.inquiryRepository.save(inquiryEntity);

        return this.inquiryRepository.findByInquiryAndResponse(InquiryTypeNameEnum.SERVICE,null).orElse(null);
    }

    @Override
    @CacheEvict(value = "inquiriesTuning", allEntries = true)
    public List<InquiryEntity> addInquiryVehicleTuning(InquiryTuningBindingModel map) {
        InquiryEntity inquiryEntity = this.modelMapper.map(map,InquiryEntity.class);
        mapInquiry(inquiryEntity);
        this.inquiryRepository.save(inquiryEntity);

        return this.inquiryRepository.findByInquiryAndResponse(InquiryTypeNameEnum.SERVICE,null).orElse(null);
    }

    @Async
     void mapInquiry(InquiryEntity inquiryEntity){
        String authentication = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity byEmail = userService.findByEmail(authentication);
        inquiryEntity.setAuthor(byEmail);
    }

    @Override
    @Cacheable("inquiriesService")
    public List <InquiryViewModel> findAllInquiriesForService() {
        List<InquiryViewModel> inquiryViewModels = new ArrayList<>();
        this.inquiryRepository.findByInquiryAndResponse(InquiryTypeNameEnum.SERVICE,null).get()
                .forEach(i -> {
                    InquiryViewModel inquiry = this.modelMapper.map(i, InquiryViewModel.class);
                    inquiryViewModels.add(inquiry);
                });
        return inquiryViewModels;
    }

    @Override
    @Cacheable("inquiriesTuning")
    public List<InquiryViewModel> findAllInquiriesForTuning() {
        List<InquiryViewModel> inquiryViewModels = new ArrayList<>();
        this.inquiryRepository.findByInquiryAndResponse(InquiryTypeNameEnum.TUNING,null).get()
                .forEach(i -> {
                    InquiryViewModel inquiry = this.modelMapper.map(i, InquiryViewModel.class);
                    inquiryViewModels.add(inquiry);
                });
        return inquiryViewModels;
    }

    @Override
    public InquiryDetailsViewModel findById(Long id) {
        Optional<InquiryEntity> byId = this.inquiryRepository.findById(id);
        InquiryDetailsViewModel inquiryDetailsViewModel = this.modelMapper.map(byId.get(), InquiryDetailsViewModel.class);
        return inquiryDetailsViewModel;
    }

    @Override
    @CacheEvict(value = { "inquiriesService", "inquiriesTuning" }, allEntries = true)
    public void addRequest(AddResponseServiceModel map) {
        InquiryEntity inquiryEntity = this.inquiryRepository.findById(map.getId()).orElse(null);
        inquiryEntity.setResponse(map.getResponse());
        this.inquiryRepository.save(inquiryEntity);
    }

    @Override
    public List<MyInquiriesViewModel> getMyInquiries(String email) {
        List<MyInquiriesViewModel> inquiryViewModels = new ArrayList<>();
        this.inquiryRepository.findByEmailOrderByResponseDesc(email).forEach(i -> {
            inquiryViewModels.add(this.modelMapper.map(i,MyInquiriesViewModel.class));
        });
        return inquiryViewModels;
    }

    @Override
    public void deleteById(Long id) {
        this.inquiryRepository.deleteById(id);
    }

    @Override
    public void agree(Long id) {
        InquiryEntity byId = this.inquiryRepository.findById(id).get();
        byId.setAgree(true);
        this.inquiryRepository.save(byId);
    }

    @Override
    public List<InquiryTaskViewModel> findTasks() {
        List<InquiryTaskViewModel> inquiryTaskViewModels = new ArrayList<>();
        this.inquiryRepository.findAllByAgree(true).forEach(i -> {
                inquiryTaskViewModels.add(modelMapper.map(i,InquiryTaskViewModel.class));
        });
        return inquiryTaskViewModels;
    }


}
