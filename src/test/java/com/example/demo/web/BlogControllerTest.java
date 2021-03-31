package com.example.demo.web;

import com.example.demo.model.entity.BlogEntity;
import com.example.demo.model.entity.RoleEntity;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.entity.enums.BlogCategoryNameEnum;
import com.example.demo.model.entity.enums.RoleNameEnum;
import com.example.demo.repository.BlogRepository;
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
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class BlogControllerTest {
    private static final String BLOG_CONTROLLER_PREFIX = "/blog";
    private Long currentId;

    @MockBean
    CloudinaryService mockCloudinaryService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;


    @BeforeEach
    public void setUp() throws IOException {
        when(mockCloudinaryService.uploadImage(Mockito.any())).thenReturn("https://res.cloudinary.com/elizabetak/image/upload/v1616681585/zjtykeegdtmrsqy8sgae.jpg");
        init();
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
        if (blogRepository.count() == 0) {
            BlogEntity blogEntity = new BlogEntity();
            blogEntity.setImageUrl("https://res.cloudinary.com/elizabetak/image/upload/v1616681585/zjtykeegdtmrsqy8sgae.jpg")
                    .setTitle("Title")
                    .setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc id erat a enim placerat maximus. Donec viverra leo neque, eu efficitur odio tincidunt ac. Maecenas sem nulla, suscipit ut orci id, scelerisque ultrices nisl. Cras venenatis elit ut tellus gravida, quis ornare elit ultrices. Sed pulvinar, neque at volutpat condimentum, tellus felis eleifend enim, nec malesuada velit ipsum scelerisque mi. Quisque mollis mollis tortor, ac tincidunt est tempor at. Fusce suscipit augue sed imperdiet dignissim. Ut metus tortor, placerat et nunc at, pellentesque aliquet metus. Pellentesque sit amet lobortis tellus, et lacinia magna.")
                    .setAuthor(userEntity)
                    .setAddedOn(LocalDateTime.now())
                    .setBlogCategory(BlogCategoryNameEnum.ROADS);
            blogRepository.save(blogEntity);
            currentId = blogEntity.getId();
        }

    }
    @Test
    @WithMockUser(username = "test@mail.com", roles = "ADMIN")
    void testShouldReturnCorrectStatusAndViewForDetails() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                BLOG_CONTROLLER_PREFIX + "/details/{id}", 1L
        )).
                andExpect(status().isOk()).
                andExpect(view().name("blog_details")).
                andExpect(model().attributeExists("blog"));
    }
    @Test
    void testShouldReturnCorrectStatusAndViewForAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(BLOG_CONTROLLER_PREFIX + "/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("blog"))
                .andExpect(model().attributeExists("blogs"));
    }

    @Test
    @WithMockUser(username = "test@mail.com", roles = "ADMIN")
    void testShouldReturnCorrectStatusAndViewForAdd() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(BLOG_CONTROLLER_PREFIX + "/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("blog_add"));
    }

    @Test
    @WithMockUser(username = "test@mail.com", roles = "ADMIN")
    void testShouldReturnCorrectStatusAndViewForAddPost() throws Exception {
        MockMultipartFile mockImgFile
                = new MockMultipartFile(
                "imageUrl",
                "hello.png",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );
        mockMvc.perform(
                MockMvcRequestBuilders.multipart(BLOG_CONTROLLER_PREFIX + "/add")
                        .file(mockImgFile)
                        .param("email", "test@mail.com")
                        .param("title", "title")
                        .param("blogCategory", String.valueOf(BlogCategoryNameEnum.BRAND))
                        .param("description", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc id erat a enim placerat maximus. Donec viverra leo neque, eu efficitur odio tincidunt ac. Maecenas sem nulla, suscipit ut orci id, scelerisque ultrices nisl. Cras venenatis elit ut tellus gravida, quis ornare elit ultrices. Sed pulvinar, neque at volutpat condimentum, tellus felis eleifend enim, nec malesuada velit ipsum scelerisque mi. Quisque mollis mollis tortor, ac tincidunt est tempor at. Fusce suscipit augue sed imperdiet dignissim. Ut metus tortor, placerat et nunc at, pellentesque aliquet metus. Pellentesque sit amet lobortis tellus, et lacinia magna.")
                        .with(csrf())).andExpect(status().is3xxRedirection());
        Assertions.assertEquals(2, blogRepository.count());


    }



    @Test
    @WithMockUser(username = "test@mail.com", roles = "ADMIN")
    void testShouldReturnCorrectStatusAndViewForAddComment() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(BLOG_CONTROLLER_PREFIX + "/details/comments/add/{id}", currentId)
                .param("comment","comment").with(csrf())).andExpect(status().is3xxRedirection());

        Assertions.assertEquals("comment",blogRepository.findById(currentId).get().getComments().get(0).getComment());
    }
}
