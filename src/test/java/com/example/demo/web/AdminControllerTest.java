package com.example.demo.web;

import com.example.demo.model.entity.RoleEntity;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.entity.enums.RoleNameEnum;
import com.example.demo.repository.NewsRepository;
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
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class AdminControllerTest {

    private static final String ADMIN_CONTROLLER_PREFIX = "/admin";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private RoleRepository roleRepository;
    @BeforeEach
    public void setUp() {
        this.init();
    }

    private void init() {
        RoleEntity user = new RoleEntity();
        user.setRole(RoleNameEnum.USER);
        roleRepository.save(user);
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("test@mail.com").setFirstName("firstName").setLastName("lastName").setPassword("password").setRoles(List.of(user));
        userRepository.save(userEntity);

    }

    @AfterEach
    public void clearDB(){
        userRepository.deleteAll();
    }

    @Test
    @WithMockUser(username = "test@.com", roles = "USER")
    void mustHave404StatusForNotAdmin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ADMIN_CONTROLLER_PREFIX + "/actions")
        ).
                andExpect(status().is(403));
    }

    @Test
    @WithMockUser(username = "test@.com", roles = "ADMIN")
    void testShouldReturnCorrectStatusAndView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ADMIN_CONTROLLER_PREFIX + "/actions")
        ).
                andExpect(status().isOk())
                .andExpect(view().name("adminActions"))
                .andExpect(model().attributeExists("allUsersEmails"));
    }
    @Test
    @WithMockUser(username = "test@.com", roles = "ADMIN")
    void addNewsShouldReturnInvalidStatusCodeAndViewForValidParams() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders.post(ADMIN_CONTROLLER_PREFIX + "/addNews")
                .param("title", "title")
                .param("description","Lorem ipsum dolor sit amet, consectetur adipiscing elit. In in semper ex, sed eleifend diam. Donec non ullamcorper lacus. Maecenas ipsum diam, facilisis non sem ac, semper venenatis risus. Ut posuere ullamcorper justo non commodo. Sed vitae purus accumsan, laoreet quam quis, posuere urna. Integer lorem lectus, porta in convallis a, cursus nec justo. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Etiam enim turpis, tristique vel sagittis a, consequat ac dolor.")
                .with(csrf())).andExpect(status().is3xxRedirection());
        Assertions.assertEquals(0,newsRepository.count());
    }
//    @Test
//    @WithMockUser(username = "test@.com", roles = "ADMIN")
//    void addRole() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post(ADMIN_CONTROLLER_PREFIX + "/role/add")
//                .param("email", "test@mail.com")
//                .param("role","ADMIN")
//                .with(csrf())).andExpect(status().is3xxRedirection());
//
//
//        List<RoleEntity> roles = userRepository.findByEmail("test@mail.com").get().getRoles();
//        System.out.println();
//    }

}
