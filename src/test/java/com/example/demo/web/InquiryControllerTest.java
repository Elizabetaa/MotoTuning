package com.example.demo.web;

import com.example.demo.model.entity.InquiryEntity;
import com.example.demo.model.entity.RoleEntity;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.entity.enums.*;
import com.example.demo.repository.InquiryRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class InquiryControllerTest {
    private static final String INQUIRY_CONTROLLER_PREFIX = "/inquiries";

    private Long testId;
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
    @AfterEach
    public void clearDB(){
        inquiryRepository.deleteAll();
        userRepository.deleteAll();
        System.out.println(inquiryRepository.count());
    }


    @Test
    @WithMockUser(username = "test@mail.com", roles = {"USER", "ADMIN"})
    void testShouldReturnValidStatusViewNameAndModel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(INQUIRY_CONTROLLER_PREFIX + "/vehicleService")
                .param("email", "test@mail.com")
                .param("phoneNumber", "1234567899")
                .param("inquiry", String.valueOf(InquiryTypeNameEnum.SERVICE))
                .param("vehicle", String.valueOf(VehicleTypeNameEnum.MOTORCYCLE))
                .param("make", String.valueOf(MakeNameEnum.DUCATI))
                .param("vehicleService", String.valueOf(ServiceTypeNameEnum.OIL))
                .param("model", "R1")
                .with(csrf())).andExpect(status().is3xxRedirection());

        Assertions.assertEquals(2, inquiryRepository.count());
    }

    @Test
    @WithMockUser(username = "test@.com", roles = "USER")
    void testShouldReturnInvalidStatusViewNameAndModel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(INQUIRY_CONTROLLER_PREFIX + "/vehicleService")
                .param("email", "test@mail.com")
                .param("phoneNumber", "1234567899")
                .param("inquiry", String.valueOf(InquiryTypeNameEnum.SERVICE))
                .param("vehicle", String.valueOf(VehicleTypeNameEnum.MOTORCYCLE))
                .param("make", String.valueOf(MakeNameEnum.DUCATI))
                .param("model", "R1")
                .with(csrf())).andExpect(status().is3xxRedirection());

        Assertions.assertEquals(1, inquiryRepository.count());
    }
    @Test
    @WithMockUser(username = "test@.com", roles = "USER")
    void detailsByIdShouldWorkCorrectly() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(INQUIRY_CONTROLLER_PREFIX + "/details/{id}", testId)
        ).
                andExpect(status().isOk()).
                andExpect(view().name("inquiry_details")).
                andExpect(model().attributeExists("inquiryEntity"));
    }
    @Test
    @WithMockUser(username = "test@.com", roles = "USER")
    void deleteByIdShouldWorkCorrectly() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(INQUIRY_CONTROLLER_PREFIX + "/delete/{id}", testId)
        ).andExpect(view().name("redirect:/users/account"));
        Assertions.assertEquals(0, inquiryRepository.count());
    }
    @Test
    @WithMockUser(username = "test@.com", roles = "USER")
    void addResponseShouldWorkCorrectly() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(INQUIRY_CONTROLLER_PREFIX + "/details/{id}",testId)
                .param("response", "some response")
                .with(csrf())).andExpect(status().is3xxRedirection());
        Assertions.assertEquals("some response",inquiryRepository.findById(testId).get().getResponse());
    }
    @Test
    @WithMockUser(username = "test@.com", roles = "USER")
    void addShouldReturnValidView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(INQUIRY_CONTROLLER_PREFIX + "/add")
        ).
                andExpect(status().isOk()).
                andExpect(view().name("add_inquiry"));
    }

    @Test
    @WithMockUser(username = "test@.com", roles = "USER")
    void testShouldReturnValidStatusViewNameAndModelForVehicleTuningPost() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(INQUIRY_CONTROLLER_PREFIX + "/vehicleTuning")
                .param("email", "test@mail.com")
                .param("phoneNumber", "1234567899")
                .param("inquiry", String.valueOf(InquiryTypeNameEnum.SERVICE))
                .param("vehicle", String.valueOf(VehicleTypeNameEnum.MOTORCYCLE))
                .param("make", String.valueOf(MakeNameEnum.DUCATI))
                .param("model", "R1")
                .param("description","description")
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
                .setService(ServiceTypeNameEnum.OIL).setMake(MakeNameEnum.SUZUKI).setModel("R1").setDescription("desc").setAuthor(userEntity);

        inquiryRepository.save(inquiryEntity);
        testId = inquiryEntity.getId();

    }
}
