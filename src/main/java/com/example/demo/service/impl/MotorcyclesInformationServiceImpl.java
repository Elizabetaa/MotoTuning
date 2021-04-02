package com.example.demo.service.impl;

import com.example.demo.config.util.ValidationUtil;
import com.example.demo.model.dto.BlogImportDro;
import com.example.demo.model.dto.InformationDto;
import com.example.demo.model.entity.BlogEntity;
import com.example.demo.model.entity.MotorcyclesInformationEntity;
import com.example.demo.model.service.ServiceInformationServiceModel;
import com.example.demo.model.view.ServiceInfoViewModel;
import com.example.demo.model.view.ServiceInformationDetailsViewModel;
import com.example.demo.repository.MotorcyclesInformationRepository;
import com.example.demo.service.MotorcyclesInformationService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class MotorcyclesInformationServiceImpl implements MotorcyclesInformationService {
    private final MotorcyclesInformationRepository motorcyclesInformationRepository;
    private final ModelMapper modelMapper;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final Gson gson;
    private final ValidationUtil validationUtil;

    public MotorcyclesInformationServiceImpl(MotorcyclesInformationRepository motorcyclesInformationRepository,
                                             ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil) {
        this.motorcyclesInformationRepository = motorcyclesInformationRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public List<ServiceInfoViewModel> getAll() {
        List<ServiceInfoViewModel> serviceInfoViewModels = new ArrayList<>();
        this.motorcyclesInformationRepository.findAll().forEach(mi -> {
            ServiceInfoViewModel map = this.modelMapper.map(mi, ServiceInfoViewModel.class);
            String pdfUrl = mi.getPdfUrl();
            map.setPrdFurl(pdfUrl);
            map.setAddedOn(mi.getAddedOn().format(formatter));
            switch (map.getMake()) {
                case YAMAHA:
                    map.setImage("/images/YAMAHA.png");
                    break;
                case HONDA:
                    map.setImage("/images/honda.png");
                    break;
                case KAWASAKI:
                    map.setImage("/images/KAWASAKI.jpg");
                    break;
                case SUZUKI:
                    map.setImage("/images/SUZUKI.jpg");
                    break;
                case DUCATI:
                    map.setImage("/images/ducati.webp");
                    break;
                case KTM:
                    map.setImage("/images/ktm-logo.png");
                    break;
                case POLARIS:
                    map.setImage("/images/POLARIS.png");
                    break;
                case HUSQVARNA:
                    map.setImage("/images/HUSQVARNA.jpg");
                    break;
                case APRILIA:
                    map.setImage("/images/aprilia.png");
                    break;
            }
            serviceInfoViewModels.add(map);
        });
        return serviceInfoViewModels;

    }

    @Override
    public void addManual(ServiceInformationServiceModel serviceInformationServiceModel) {
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

    public String readPassengersFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of("src/main/resources/static/files/information.json")));
    }

    @Override
    public void initInfo() throws IOException {
        if (motorcyclesInformationRepository.count() == 0) {
            InformationDto[] informationDtos = this.gson.fromJson(readPassengersFileContent(), InformationDto[].class);
            for (InformationDto importDto : informationDtos) {
                if (validationUtil.isValid(importDto)) {
                    MotorcyclesInformationEntity motorcyclesInformationEntity = this.modelMapper.map(importDto, MotorcyclesInformationEntity.class);

                    this.motorcyclesInformationRepository.save(motorcyclesInformationEntity);
                }
            }
        }
    }


}
