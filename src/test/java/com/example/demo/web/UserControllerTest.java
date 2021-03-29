package com.example.demo.web;

import com.example.demo.model.entity.RoleEntity;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.entity.enums.RoleNameEnum;
import com.example.demo.repository.NewsRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CloudinaryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class UserControllerTest {
    private static final String USER_CONTROLLER_PREFIX = "/users";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @MockBean
    CloudinaryService mockCloudinaryService;
    @BeforeEach
    public void setUp() throws IOException {
        when(mockCloudinaryService.uploadImage(Mockito.any())).thenReturn("https://res.cloudinary.com/elizabetak/image/upload/v1616681585/zjtykeegdtmrsqy8sgae.jpg");
        init();
    }
    @Test
    void registerShouldReturnValidStatusAndView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                USER_CONTROLLER_PREFIX + "/register"
        )).
                andExpect(status().isOk()).
                andExpect(view().name("register"));
    }
    @Test
    void signInShouldReturnValidStatusAndView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                USER_CONTROLLER_PREFIX + "/signIn"
        )).
                andExpect(status().isOk()).
                andExpect(view().name("signIn"));
    }
    @Test
    void registerShouldReturnValidStatusAndAddUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(
                USER_CONTROLLER_PREFIX + "/register")
                        .param("firstName", "FirstName")
                        .param("lastName", "lastName")
                        .param("email", "demo@mail.com")
                        .param("password", "password")
                        .param("confirmPassword", "password")
                .with(csrf())).andExpect(status().is3xxRedirection());
        Assertions.assertEquals(2,userRepository.count());
    }
    @Test
    void signInShouldReturnValidStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(
                USER_CONTROLLER_PREFIX + "/signIn")
                        .param("email", "demo@mail.com")
                        .param("password", "password")
                .with(csrf())).andExpect(status().is2xxSuccessful());
    }

    @Test
    @WithMockUser(username = "test@mail.com", roles = "ADMIN")
    void accountShouldReturnValidStatusAndView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                USER_CONTROLLER_PREFIX + "/account"
        )).
                andExpect(status().isOk()).
                andExpect(model().attributeExists("currentUser")).
                andExpect(model().attributeExists("myInquiries")).
                andExpect(view().name("myAccount"));
    }
    @Test
    @WithMockUser(username = "test@mail.com", roles = "ADMIN")
    void editAccountShouldReturnValidStatusAndView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                USER_CONTROLLER_PREFIX + "/editAccount"
        )).
                andExpect(status().isOk()).
                andExpect(view().name("edit-account"));
    }
    @Test
    @WithMockUser(username = "test@mail.com", roles = "ADMIN")
    void editAccountShouldReturnInvalidStatusAndChangeUserParams() throws Exception {
        Optional<UserEntity> before = userRepository.findByEmail("test@mail.com");
        mockMvc.perform(MockMvcRequestBuilders.post(
                USER_CONTROLLER_PREFIX + "/editAccount")
                .param("firstName", "e")
                .param("editedLastName", "e")
                .with(csrf())).andExpect(status().is3xxRedirection());

        Optional<UserEntity> byEmail = userRepository.findByEmail("test@mail.com");
        Assertions.assertEquals(before.get().getFirstName(),byEmail.get().getFirstName());
        Assertions.assertEquals(before.get().getLastName(),byEmail.get().getLastName());
    }
    @Test
    @WithMockUser(username = "test@mail.com", roles = "ADMIN")
    void editAccountShouldReturnValidStatusAndChangeUserParams() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(
                USER_CONTROLLER_PREFIX + "/editAccount")
                .param("firstName", "editedFirstName")
                .param("lastName", "editedLastName")
                .with(csrf())).andExpect(status().is3xxRedirection());

        Optional<UserEntity> byEmail = userRepository.findByEmail("test@mail.com");
        Assertions.assertEquals("editedFirstName",byEmail.get().getFirstName());
        Assertions.assertEquals("editedLastName",byEmail.get().getLastName());
    }


    private void init() {
        UserEntity userEntity = new UserEntity();

        if (roleRepository.count() == 0) {
            RoleEntity user = new RoleEntity();
            user.setRole(RoleNameEnum.USER);
            roleRepository.save(user);
            userEntity.setEmail("test@mail.com").setFirstName("firstName").setLastName("lastName").setPassword("password").setRoles(List.of(user));
            userRepository.save(userEntity);
        }
        if (userRepository.count() == 0){
            RoleEntity byRole = roleRepository.findByRole(RoleNameEnum.USER).get();
            userEntity.setEmail("test@mail.com").setFirstName("firstName").setLastName("lastName").setPassword("password").setRoles(List.of(byRole))
                    .setImageUrl("https://res.cloudinary.com/elizabetak/image/upload/v1616422750/default-user-Img_dpqzkv.png");
            userRepository.save(userEntity);
        }
    }
}
