package com.example.demo.web;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class AdminControllerTest {

    private static final String ADMIN_CONTROLLER_PREFIX = "/admin";



    @BeforeEach
    public void setUp(){
        this.init();
    }

    private void init() {

    }
}
