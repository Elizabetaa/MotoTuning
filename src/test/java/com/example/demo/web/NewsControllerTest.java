package com.example.demo.web;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.example.demo.model.entity.NewsEntity;
import com.example.demo.repository.NewsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class NewsControllerTest {
    private static final String NEWS_CONTROLLER_PREFIX = "/news";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private NewsRepository newsRepository;

    @BeforeEach
    public void setUp(){
        this.init();
    }

//    @Test
//    @WithMockUser(username = "admin@",roles = {"USER","ADMIN"})
//    void testShouldReturnValidStatusViewNameAndModel() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get(
//                NEWS_CONTROLLER_PREFIX + "/all"
//        ))
//                .andExpect(status().isOk());
//    }

    private void init() {
        NewsEntity newsEntity = new NewsEntity();
        newsEntity.setAddedOn(LocalDateTime.now());
        newsEntity.setTitle("Title");
        newsEntity.setDescription("description");

        newsRepository.save(newsEntity);
    }
}
