package com.example.demo.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class HomeControllerTest {

    private static final String HOME_CONTROLLER_PREFIX = "/home";
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testShouldReturnValidStatusAndViewIndex() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get( "/"
        )).
                andExpect(status().isOk()).
                andExpect(view().name("home"));
    }
    @Test
    void testShouldReturnValidStatusAndViewHome() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                HOME_CONTROLLER_PREFIX
        )).
                andExpect(status().isOk()).
                andExpect(view().name("home"));
    }
    @Test
    void testShouldReturnValidStatusAndViewHomeMotorcycle() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                HOME_CONTROLLER_PREFIX + "/motorcycle"
        )).
                andExpect(status().isOk()).
                andExpect(view().name("motorcyclePassion"));
    }
    @Test
    void testShouldReturnValidStatusAndViewHomeMotorcycleUtc() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                HOME_CONTROLLER_PREFIX + "/utv"
        )).
                andExpect(status().isOk()).
                andExpect(view().name("utvPassion"));
    }
}
