package com.example.demo.services;

import com.example.demo.model.entity.InquiryEntity;
import com.example.demo.model.entity.RoleEntity;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.entity.enums.*;
import com.example.demo.model.service.AddResponseServiceModel;
import com.example.demo.model.view.InquiryDetailsViewModel;
import com.example.demo.model.view.InquiryViewModel;
import com.example.demo.model.view.MyInquiriesViewModel;
import com.example.demo.repository.InquiryRepository;
import com.example.demo.service.InquiryService;
import com.example.demo.service.UserService;
import com.example.demo.service.impl.InquiryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)

public class InquiryServiceTest {
    private InquiryService serviceTest;

    @Mock
    InquiryRepository mockInquiryRepository;
    @Mock
    ModelMapper mockModelMapper = new ModelMapper();
    @Mock
    UserService mockUserService;

    @BeforeEach
    public void setUp() {
        serviceTest = new InquiryServiceImpl(mockInquiryRepository,mockModelMapper,mockUserService);
    }

    @Test
    void findAllInquiriesForServiceTest(){
        RoleEntity user = new RoleEntity();
        user.setRole(RoleNameEnum.USER);
        RoleEntity admin = new RoleEntity();
        admin.setRole(RoleNameEnum.ADMIN);

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("test@abv.bg").setPassword("password");
        userEntity.setRoles(List.of(user,admin));

        InquiryViewModel inquiryViewModel = new InquiryViewModel();
        inquiryViewModel.setId(1L).setEmail("test@abv.bg").setVehicle(VehicleTypeNameEnum.MOTORCYCLE).setModel("R1");

        InquiryEntity inquiryEntity = new InquiryEntity();
        inquiryEntity.setInquiry(InquiryTypeNameEnum.SERVICE).setEmail("test@abv.bg").setPhoneNumber("0123456789")
                .setVehicle(VehicleTypeNameEnum.MOTORCYCLE).setService(ServiceTypeNameEnum.OIL)
                .setMake(MakeNameEnum.SUZUKI).setModel("R1").setDescription("desc").setAuthor(userEntity);


        Mockito.when(mockInquiryRepository.findByInquiryAndResponse(InquiryTypeNameEnum.SERVICE, null))
                .thenReturn(Optional.of(List.of(inquiryEntity)));

        List<InquiryViewModel> allInquiriesForService = serviceTest.findAllInquiriesForService();

        Assertions.assertEquals(1,allInquiriesForService.size());

    }

    @Test
    void findAllInquiriesForTuningTest(){
        RoleEntity user = new RoleEntity();
        user.setRole(RoleNameEnum.USER);
        RoleEntity admin = new RoleEntity();
        admin.setRole(RoleNameEnum.ADMIN);

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("test@abv.bg").setPassword("password");
        userEntity.setRoles(List.of(user,admin));

        InquiryViewModel inquiryViewModel = new InquiryViewModel();
        inquiryViewModel.setId(1L).setEmail("test@abv.bg").setVehicle(VehicleTypeNameEnum.MOTORCYCLE).setModel("R1");

        InquiryEntity inquiryEntity = new InquiryEntity();
        inquiryEntity.setInquiry(InquiryTypeNameEnum.TUNING).setEmail("test@abv.bg").setPhoneNumber("0123456789")
                .setVehicle(VehicleTypeNameEnum.MOTORCYCLE).setService(ServiceTypeNameEnum.OIL)
                .setMake(MakeNameEnum.SUZUKI).setModel("R1").setDescription("desc").setAuthor(userEntity);


        Mockito.when(mockInquiryRepository.findByInquiryAndResponse(InquiryTypeNameEnum.TUNING, null))
                .thenReturn(Optional.of(List.of(inquiryEntity)));

        List<InquiryViewModel> allInquiriesForService = serviceTest.findAllInquiriesForTuning();

        Assertions.assertEquals(1,allInquiriesForService.size());

    }
    @Test
    void findById(){
        RoleEntity user = new RoleEntity();
        user.setRole(RoleNameEnum.USER);
        RoleEntity admin = new RoleEntity();
        admin.setRole(RoleNameEnum.ADMIN);


        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("test@abv.bg").setPassword("password");
        userEntity.setRoles(List.of(user,admin));

        InquiryEntity inquiryEntity = new InquiryEntity();
        inquiryEntity.setInquiry(InquiryTypeNameEnum.TUNING).setEmail("test@abv.bg").setPhoneNumber("0123456789")
                .setVehicle(VehicleTypeNameEnum.MOTORCYCLE).setService(ServiceTypeNameEnum.OIL)
                .setMake(MakeNameEnum.SUZUKI).setModel("R1").setDescription("desc").setAuthor(userEntity).setId(1L);

        Mockito.when(mockInquiryRepository.findById(inquiryEntity.getId())).thenReturn(Optional.of(inquiryEntity));
        InquiryDetailsViewModel inquiryDetailsViewModel = new InquiryDetailsViewModel();
        inquiryDetailsViewModel.setInquiry(InquiryTypeNameEnum.TUNING).setEmail("test@abv.bg").setPhoneNumber("0123456789")
                .setVehicle(VehicleTypeNameEnum.MOTORCYCLE).setService(ServiceTypeNameEnum.OIL)
                .setMake(MakeNameEnum.SUZUKI).setModel("R1").setDescription("desc").setAuthor(userEntity).setId(1L);
        Mockito.when(mockModelMapper.map(inquiryEntity,InquiryDetailsViewModel.class)).thenReturn(inquiryDetailsViewModel);
        InquiryDetailsViewModel byId = serviceTest.findById(inquiryEntity.getId());
        Assertions.assertEquals(inquiryDetailsViewModel,byId);
        Assertions.assertEquals(inquiryDetailsViewModel.getAuthor(),byId.getAuthor());
    }

    @Test
    void getMyInquiriesTest(){
        RoleEntity user = new RoleEntity();
        user.setRole(RoleNameEnum.USER);
        RoleEntity admin = new RoleEntity();
        admin.setRole(RoleNameEnum.ADMIN);


        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("test@abv.bg").setPassword("password");
        userEntity.setRoles(List.of(user,admin));

        InquiryEntity inquiryEntity = new InquiryEntity();
        inquiryEntity.setInquiry(InquiryTypeNameEnum.TUNING).setEmail("test@abv.bg").setPhoneNumber("0123456789")
                .setVehicle(VehicleTypeNameEnum.MOTORCYCLE).setService(ServiceTypeNameEnum.OIL)
                .setMake(MakeNameEnum.SUZUKI).setModel("R1").setDescription("desc").setAuthor(userEntity).setId(1L);

        Mockito.when(mockInquiryRepository.findByEmailOrderByResponseDesc("test@abv.bg")).thenReturn(List.of(inquiryEntity));
        MyInquiriesViewModel inquiriesViewModel = new MyInquiriesViewModel();
        inquiriesViewModel.setId(1L);
        inquiriesViewModel.setVehicle(VehicleTypeNameEnum.MOTORCYCLE);
        inquiriesViewModel.setModel("R1");
        inquiriesViewModel.setResponse("response");

        Mockito.when(mockModelMapper.map(inquiryEntity,MyInquiriesViewModel.class)).thenReturn(inquiriesViewModel);

        List<MyInquiriesViewModel> myInquiries = serviceTest.getMyInquiries("test@abv.bg");
        Assertions.assertEquals(List.of(inquiriesViewModel),myInquiries);
    }

    @Test
    void addRequestTest(){
        AddResponseServiceModel addResponseServiceModel = new AddResponseServiceModel();
        addResponseServiceModel.setId(1L);
        addResponseServiceModel.setResponse("response");

        RoleEntity user = new RoleEntity();
        user.setRole(RoleNameEnum.USER);
        RoleEntity admin = new RoleEntity();
        admin.setRole(RoleNameEnum.ADMIN);


        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("test@abv.bg").setPassword("password");
        userEntity.setRoles(List.of(user,admin));

        InquiryEntity inquiryEntity = new InquiryEntity();
        inquiryEntity.setInquiry(InquiryTypeNameEnum.TUNING).setEmail("test@abv.bg").setPhoneNumber("0123456789")
                .setVehicle(VehicleTypeNameEnum.MOTORCYCLE).setService(ServiceTypeNameEnum.OIL)
                .setMake(MakeNameEnum.SUZUKI).setModel("R1").setDescription("desc").setAuthor(userEntity).setId(1L);

        Mockito.when(mockInquiryRepository.findById(1L)).thenReturn(Optional.of(inquiryEntity));

        serviceTest.addRequest(addResponseServiceModel);

        Mockito.verify(mockInquiryRepository).save(inquiryEntity);
    }

    @Test
    void deleteById(){
        serviceTest.deleteById(1L);
        Mockito.verify(mockInquiryRepository).deleteById(1L);
    }
}
