package com.example.demo.web;

import com.example.demo.model.view.ServiceInfoViewModel;
import com.example.demo.service.MotorcyclesInformationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping ("/service")
public class ServiceInformationRestController {
    private final MotorcyclesInformationService motorcyclesInformationService;

    public ServiceInformationRestController(MotorcyclesInformationService motorcyclesInformationService) {
        this.motorcyclesInformationService = motorcyclesInformationService;
    }

    @GetMapping("/informationApi")
    public ResponseEntity<List<ServiceInfoViewModel>> getInfo(){
        return ResponseEntity
                .ok()
                .body(motorcyclesInformationService.getAll());
    }

}
