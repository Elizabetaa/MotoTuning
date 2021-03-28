package com.example.demo.web;

import com.example.demo.model.entity.InquiryEntity;
import com.example.demo.model.entity.RoleEntity;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.entity.enums.*;
import com.example.demo.repository.InquiryRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class InquiryControllerTest {
    private static final String INQUIRY_CONTROLLER_PREFIX = "/inquiries";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private InquiryRepository inquiryRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @BeforeEach
    public void setUp() {
        this.init();
    }

    private InquiryTypeNameEnum inquiry;
    private VehicleTypeNameEnum vehicle;
    private BrandsNameEnum brand;
    private String model;
    private String description;

    @Test
    @WithMockUser(username = "test@mail.com", roles = {"USER", "ADMIN"})
    void testShouldReturnValidStatusViewNameAndModel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(INQUIRY_CONTROLLER_PREFIX + "/vehicleService")
                .param("email", "test@mail.com")
                .param("phoneNumber", "1234567899")
                .param("inquiry", String.valueOf(InquiryTypeNameEnum.SERVICE))
                .param("vehicle", String.valueOf(VehicleTypeNameEnum.MOTORCYCLE))
                .param("brand", String.valueOf(BrandsNameEnum.DUCATI))
                .param("model", "R1")
                .with(csrf())).andExpect(status().is3xxRedirection());

        Assertions.assertEquals(2, inquiryRepository.count());
    }


    private void init() {
        RoleEntity user = new RoleEntity();
        user.setRole(RoleNameEnum.USER);
        RoleEntity admin = new RoleEntity();
        admin.setRole(RoleNameEnum.ADMIN);
        roleRepository.save(user);
        roleRepository.save(admin);

        user.setId(1L);
        admin.setId(2L);
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("test@mail.com").setFirstName("firstName").setLastName("lastName").setPassword("password").setRoles(List.of(user, admin));
        userRepository.save(userEntity);
        InquiryEntity inquiryEntity = new InquiryEntity();
        inquiryEntity.setInquiry(InquiryTypeNameEnum.SERVICE).setEmail("test@mail.com").setPhoneNumber("088888888888").setVehicle(VehicleTypeNameEnum.MOTORCYCLE)
                .setService(ServiceTypeNameEnum.OIL).setBrand(BrandsNameEnum.SUZUKI).setModel("R1").setDescription("desc").setAuthor(userEntity);

        inquiryRepository.save(inquiryEntity);

    }
}
