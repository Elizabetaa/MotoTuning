package com.example.demo.service.impl;

import com.example.demo.model.entity.MotorcyclesInformationEntity;
import com.example.demo.model.service.ServiceInformationServiceModel;
import com.example.demo.model.view.ServiceInfoViewModel;
import com.example.demo.model.view.ServiceInformationDetailsViewModel;
import com.example.demo.repository.MotorcyclesInformationRepository;
import com.example.demo.service.MotorcyclesInformationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class MotorcyclesInformationServiceImpl implements MotorcyclesInformationService {
    private final MotorcyclesInformationRepository motorcyclesInformationRepository;
    private final ModelMapper modelMapper;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    public MotorcyclesInformationServiceImpl(MotorcyclesInformationRepository motorcyclesInformationRepository,
                                             ModelMapper modelMapper ) {
        this.motorcyclesInformationRepository = motorcyclesInformationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ServiceInfoViewModel> getAll() {
        List<ServiceInfoViewModel> serviceInfoViewModels = new ArrayList<>();
        this.motorcyclesInformationRepository.findAll().forEach(mi -> {
            ServiceInfoViewModel map = this.modelMapper.map(mi, ServiceInfoViewModel.class);
            map.setAddedOn(mi.getAddedOn().format(formatter));
            serviceInfoViewModels.add(map);
        });
        return serviceInfoViewModels;

    }

    @Override
    public void addManual(ServiceInformationServiceModel serviceInformationServiceModel)  {
        MotorcyclesInformationEntity motorcyclesInformationEntity = this.modelMapper.map(serviceInformationServiceModel, MotorcyclesInformationEntity.class);
        this.motorcyclesInformationRepository.save(motorcyclesInformationEntity);
    }

    @Override
    public ServiceInformationDetailsViewModel getById(Long id) {
        MotorcyclesInformationEntity entity = this.motorcyclesInformationRepository.findById(id).get();
        ServiceInformationDetailsViewModel map = this.modelMapper.map(entity, ServiceInformationDetailsViewModel.class);
        map.setAddedOn(entity.getAddedOn().format(formatter));
        return map;
    }


}
