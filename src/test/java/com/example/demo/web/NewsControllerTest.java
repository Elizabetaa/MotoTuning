package com.example.demo.web;

import com.example.demo.model.entity.NewsEntity;
import com.example.demo.repository.NewsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class NewsControllerTest {

    private static final String NEWS_CONTROLLER_PREFIX = "/news";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private NewsRepository newsRepository;

    @Test
    @WithMockUser(value = "test@", roles = "USER")
    void testShouldReturnValidStatusAndView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get( NEWS_CONTROLLER_PREFIX+"/all"
        )).
                andExpect(status().isOk()).
                andExpect(model().attributeExists("news")).
                andExpect(view().name("news"));
    }
    @Test
    @WithMockUser(value = "test@", roles = "USER")
    void testShouldReturnValidStatusAndViewIndexDetails() throws Exception {
        NewsEntity newsEntity = new NewsEntity();
        newsEntity.setTitle("test").setImageUrl("https://res.cloudinary.com/elizabetak/image/upload/v1616681585/zjtykeegdtmrsqy8sgae.jpg")
                .setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc id erat a enim placerat maximus. Donec viverra leo neque, eu efficitur odio tincidunt ac. Maecenas sem nulla, suscipit ut orci id, scelerisque ultrices nisl. Cras venenatis elit ut tellus gravida, quis ornare elit ultrices. Sed pulvinar, neque at volutpat condimentum, tellus felis eleifend enim, nec malesuada velit ipsum scelerisque mi. Quisque mollis mollis tortor, ac tincidunt est tempor at. Fusce suscipit augue sed imperdiet dignissim. Ut metus tortor, placerat et nunc at, pellentesque aliquet metus. Pellentesque sit amet lobortis tellus, et lacinia magna.")
                .setAddedOn(LocalDateTime.now());
        newsRepository.save(newsEntity);

        mockMvc.perform(MockMvcRequestBuilders.get( NEWS_CONTROLLER_PREFIX+"/details/{id}",newsEntity.getId()
        )).
                andExpect(status().isOk()).
                andExpect(model().attributeExists("news")).
                andExpect(view().name("news_details"));
    }
}
