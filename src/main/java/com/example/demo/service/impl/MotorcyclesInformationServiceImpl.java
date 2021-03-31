package com.example.demo.service.impl;

import com.example.demo.model.view.ServiceInfoViewModel;
import com.example.demo.repository.MotorcyclesInformationRepository;
import com.example.demo.service.MotorcyclesInformationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MotorcyclesInformationServiceImpl implements MotorcyclesInformationService {
    private final MotorcyclesInformationRepository motorcyclesInformationRepository;
    private final ModelMapper modelMapper;

    public MotorcyclesInformationServiceImpl(MotorcyclesInformationRepository motorcyclesInformationRepository, ModelMapper modelMapper) {
        this.motorcyclesInformationRepository = motorcyclesInformationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ServiceInfoViewModel> getAll() {
        List<ServiceInfoViewModel> serviceInfoViewModels = new ArrayList<>();
        this.motorcyclesInformationRepository.findAll().forEach(mi -> {
            serviceInfoViewModels.add(this.modelMapper.map(mi, ServiceInfoViewModel.class));
        });
        return serviceInfoViewModels;
    }
}
