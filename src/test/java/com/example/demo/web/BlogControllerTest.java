package com.example.demo.web;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class BlogControllerTest {

    private static final String BLOG_CONTROLLER_PREFIX = "/blog";

}
