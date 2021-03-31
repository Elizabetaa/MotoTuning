package com.example.demo.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class ForUsControllerTest {
    private static final String FOR_US_CONTROLLER_PREFIX = "/tuning";
    @Autowired
    private MockMvc mockMvc;
    @Test
    void testShouldReturnValidStatusAndView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                FOR_US_CONTROLLER_PREFIX + "/contacts"
        )).
                andExpect(status().isOk()).
                andExpect(view().name("contacts"));
    }
    @Test
    void testShouldReturnValidStatusAndViewAboutUs() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                FOR_US_CONTROLLER_PREFIX + "/aboutUs"
        )).
                andExpect(status().isOk()).
                andExpect(view().name("about_us"));
    }
}
