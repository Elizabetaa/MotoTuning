package com.example.demo.services;


import com.example.demo.config.util.ValidationUtil;
import com.example.demo.model.entity.BlogEntity;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.entity.enums.BlogCategoryNameEnum;
import com.example.demo.model.view.BlogDetailsViewModel;
import com.example.demo.model.view.BlogViewModel;
import com.example.demo.repository.BlogRepository;
import com.example.demo.service.CloudinaryService;
import com.example.demo.service.UserService;
import com.example.demo.service.impl.BlogServiceImpl;
import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BlogServiceTest {

    private BlogServiceImpl blogService;

    @Mock
    ModelMapper modelMapper;
    @Mock
    BlogRepository blogRepository;
    @Mock
    CloudinaryService cloudinaryService;
    @Mock
    UserService userService;

    @BeforeEach
    public void setUp() {
        modelMapper = new ModelMapper();
        blogService = new BlogServiceImpl(modelMapper, blogRepository, cloudinaryService, userService);
    }


    @Test
    void findByRoadCategory() {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("test@abv.bg").setPassword("password");
        BlogEntity ROADS = new BlogEntity();
        ROADS.setImageUrl("imageUrl").setAuthor(userEntity).setDescription("desc").setTitle("ROADS").setBlogCategory(BlogCategoryNameEnum.ROADS);
        BlogEntity MOTORCYCLE = new BlogEntity();
        MOTORCYCLE.setImageUrl("imageUrl").setAuthor(userEntity).setDescription("desc").setTitle("MOTORCYCLE").setBlogCategory(BlogCategoryNameEnum.MOTORCYCLE);
        BlogEntity BRAND = new BlogEntity();
        BRAND.setImageUrl("imageUrl").setAuthor(userEntity).setDescription("desc").setTitle("BRAND").setBlogCategory(BlogCategoryNameEnum.BRAND);
        BlogEntity TUNING = new BlogEntity();
        TUNING.setImageUrl("imageUrl").setAuthor(userEntity).setDescription("desc").setTitle("TUNING").setBlogCategory(BlogCategoryNameEnum.TUNING);

        Mockito.when(blogRepository.findByBlogCategory(BlogCategoryNameEnum.ROADS)).thenReturn(List.of(ROADS));
        List<BlogViewModel> byRoad = blogService.findByCategory(BlogCategoryNameEnum.ROADS);
        Assertions.assertEquals(modelMapper.map(ROADS, BlogViewModel.class).getTitle(), byRoad.get(0).getTitle());

        Mockito.when(blogRepository.findByBlogCategory(BlogCategoryNameEnum.MOTORCYCLE)).thenReturn(List.of(MOTORCYCLE));
        List<BlogViewModel> byMotorcycle = blogService.findByCategory(BlogCategoryNameEnum.MOTORCYCLE);
        Assertions.assertEquals(modelMapper.map(MOTORCYCLE, BlogViewModel.class).getTitle(), byMotorcycle.get(0).getTitle());

        Mockito.when(blogRepository.findByBlogCategory(BlogCategoryNameEnum.BRAND)).thenReturn(List.of(BRAND));
        List<BlogViewModel> byBrand = blogService.findByCategory(BlogCategoryNameEnum.BRAND);
        Assertions.assertEquals(modelMapper.map(BRAND, BlogViewModel.class).getTitle(), byBrand.get(0).getTitle());

        Mockito.when(blogRepository.findByBlogCategory(BlogCategoryNameEnum.TUNING)).thenReturn(List.of(TUNING));
        List<BlogViewModel> byTuning = blogService.findByCategory(BlogCategoryNameEnum.TUNING);
        Assertions.assertEquals(modelMapper.map(TUNING, BlogViewModel.class).getTitle(), byTuning.get(0).getTitle());

    }

    @Test
    void findByIdTest() {
        BlogEntity blogEntity = new BlogEntity();
        blogEntity.setImageUrl("imageUrl").setDescription("desc").setTitle("title").setAddedOn(LocalDateTime.now()).setBlogCategory(BlogCategoryNameEnum.ROADS);

        Mockito.when(blogRepository.findById(1L)).thenReturn(Optional.of(blogEntity));
        BlogDetailsViewModel blogDetailsViewModel = modelMapper.map(blogEntity, BlogDetailsViewModel.class);

        BlogDetailsViewModel byId = blogService.findById(1L);

        Assertions.assertEquals(blogDetailsViewModel.getTitle(), byId.getTitle());

    }

    @Test
    void findFirstFourTest() {
        BlogEntity blogEntity1 = new BlogEntity();
        blogEntity1.setTitle("title1").setAddedOn(LocalDateTime.of(2017, 2, 13, 15, 56));

        BlogEntity blogEntity2 = new BlogEntity();
        blogEntity2.setTitle("title2").setAddedOn(LocalDateTime.of(2018, 2, 13, 15, 56));

        BlogEntity blogEntity3 = new BlogEntity();
        blogEntity3.setTitle("title3").setAddedOn(LocalDateTime.of(2019, 2, 13, 15, 56));

        BlogEntity blogEntity4 = new BlogEntity();
        blogEntity4.setTitle("title4").setAddedOn(LocalDateTime.of(2020, 2, 13, 15, 56));

        BlogEntity blogEntity5 = new BlogEntity();
        blogEntity5.setTitle("title5").setAddedOn(LocalDateTime.of(2021, 2, 13, 15, 56));

        Mockito.when(blogRepository.findTop4ByOrderByAddedOnDesc()).thenReturn(List.of(blogEntity2, blogEntity3, blogEntity4, blogEntity5));

        List<BlogViewModel> firstFour = blogService.findFirstFour();
        modelMapper.map(blogEntity2, BlogViewModel.class);
        Assertions.assertEquals(modelMapper.map(blogEntity2, BlogViewModel.class).getTitle(), firstFour.get(0).getTitle());
        Assertions.assertEquals(modelMapper.map(blogEntity3, BlogViewModel.class).getTitle(), firstFour.get(1).getTitle());
        Assertions.assertEquals(modelMapper.map(blogEntity4, BlogViewModel.class).getTitle(), firstFour.get(2).getTitle());
        Assertions.assertEquals(modelMapper.map(blogEntity5, BlogViewModel.class).getTitle(), firstFour.get(3).getTitle());
    }

}
