package com.example.demo.service;

import com.example.demo.model.service.ServiceInformationServiceModel;
import com.example.demo.model.view.ServiceInfoViewModel;
import com.example.demo.model.view.ServiceInformationDetailsViewModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface MotorcyclesInformationService {
    List<ServiceInfoViewModel> getAll();

    void addManual(ServiceInformationServiceModel map) throws IOException, SQLException;

    ServiceInformationDetailsViewModel getById(Long id);
}
