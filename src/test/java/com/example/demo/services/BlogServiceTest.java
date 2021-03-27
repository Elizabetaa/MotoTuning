package com.example.demo.services;


import com.example.demo.model.entity.BlogEntity;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.entity.enums.BlogCategoryNameEnum;
import com.example.demo.model.service.AddBlogServiceModel;
import com.example.demo.model.view.BlogDetailsViewModel;
import com.example.demo.model.view.BlogViewModel;
import com.example.demo.repository.BlogRepository;
import com.example.demo.service.CloudinaryService;
import com.example.demo.service.impl.BlogServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
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

    @BeforeEach
    public void setUp() {
        modelMapper = new ModelMapper();
        blogService = new BlogServiceImpl(modelMapper, blogRepository, cloudinaryService);
    }


    @Test
    void findByRoadTest() {
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
        Assertions.assertEquals(modelMapper.map(ROADS,BlogViewModel.class).getTitle(),byRoad.get(0).getTitle());

        Mockito.when(blogRepository.findByBlogCategory(BlogCategoryNameEnum.MOTORCYCLE)).thenReturn(List.of(MOTORCYCLE));
        List<BlogViewModel> byMotorcycle = blogService.findByCategory(BlogCategoryNameEnum.MOTORCYCLE);
        Assertions.assertEquals(modelMapper.map(MOTORCYCLE,BlogViewModel.class).getTitle(),byMotorcycle.get(0).getTitle());

        Mockito.when(blogRepository.findByBlogCategory(BlogCategoryNameEnum.BRAND)).thenReturn(List.of(BRAND));
        List<BlogViewModel> byBrand = blogService.findByCategory(BlogCategoryNameEnum.BRAND);
        Assertions.assertEquals(modelMapper.map(BRAND,BlogViewModel.class).getTitle(),byBrand.get(0).getTitle());

        Mockito.when(blogRepository.findByBlogCategory(BlogCategoryNameEnum.TUNING)).thenReturn(List.of(TUNING));
        List<BlogViewModel> byTuning = blogService.findByCategory(BlogCategoryNameEnum.TUNING);
        Assertions.assertEquals(modelMapper.map(TUNING,BlogViewModel.class).getTitle(),byTuning.get(0).getTitle());

    }

    @Test
    void findByIdTest(){
        BlogEntity blogEntity = new BlogEntity();
        blogEntity.setImageUrl("imageUrl").setDescription("desc").setTitle("title").setBlogCategory(BlogCategoryNameEnum.ROADS);

        Mockito.when(blogRepository.findById(1L)).thenReturn(Optional.of(blogEntity));
        BlogDetailsViewModel blogDetailsViewModel = modelMapper.map(blogEntity,BlogDetailsViewModel.class);

        BlogDetailsViewModel byId = blogService.findById(1L);

        Assertions.assertEquals(blogDetailsViewModel.getTitle(),byId.getTitle());

    }

}
