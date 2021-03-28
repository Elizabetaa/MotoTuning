package com.example.demo.web;

import com.example.demo.repository.InquiryRepository;
import com.example.demo.repository.NewsRepository;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class AdminControllerTest {

    private static final String ADMIN_CONTROLLER_PREFIX = "/admin";

    @Autowired
    private InquiryRepository inquiryRepository;
    @Autowired
    NewsRepository newsRepository;
    @Autowired
    UserRepository userRepository;



    @BeforeEach
    public void setUp(){
        this.init();
    }

    private void init() {

    }
}
