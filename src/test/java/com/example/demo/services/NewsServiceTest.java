package com.example.demo.services;

import com.example.demo.model.entity.NewsEntity;
import com.example.demo.model.entity.enums.InquiryTypeNameEnum;
import com.example.demo.model.view.InquiryDetailsViewModel;
import com.example.demo.model.view.NewsDetailsViewModel;
import com.example.demo.repository.InquiryRepository;
import com.example.demo.repository.NewsRepository;
import com.example.demo.service.CloudinaryService;
import com.example.demo.service.UserService;
import com.example.demo.service.impl.InquiryServiceImpl;
import com.example.demo.service.impl.NewsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)

public class NewsServiceTest {
    private NewsServiceImpl serviceTest;
    @Mock
    NewsRepository mockNewsRepository;
    @Mock
    ModelMapper mockModelMapper = new ModelMapper();
    @Mock
    CloudinaryService cloudinaryService;

    @BeforeEach
    public void setUp() {
        serviceTest = new NewsServiceImpl(mockModelMapper, mockNewsRepository, cloudinaryService);
    }

    @Test
    void findById() {
        NewsDetailsViewModel newsDetailsViewModel = new NewsDetailsViewModel();
        newsDetailsViewModel.setTitle("title");
        newsDetailsViewModel.setImageUrl("url");
        newsDetailsViewModel.setDescription("desc");

        NewsEntity newsEntity = new NewsEntity();
        newsEntity.setTitle("title")
                .setImageUrl("url")
                .setDescription("desc");

        Mockito.when(mockNewsRepository.findById(1L)).thenReturn(Optional.of(newsEntity));
        Mockito.when(mockModelMapper.map(newsEntity, NewsDetailsViewModel.class)).thenReturn(newsDetailsViewModel);

        NewsDetailsViewModel byId = serviceTest.findById(1L);

        Assertions.assertEquals(newsDetailsViewModel,byId);

    }

}
