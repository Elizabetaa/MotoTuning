package com.example.demo.services;

import com.example.demo.model.entity.NewsEntity;
import com.example.demo.model.entity.enums.InquiryTypeNameEnum;
import com.example.demo.model.view.InquiryDetailsViewModel;
import com.example.demo.model.view.NewsDetailsViewModel;
import com.example.demo.model.view.NewsViewModel;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)

public class NewsServiceTest {
    private NewsServiceImpl serviceTest;
    @Mock
    NewsRepository mockNewsRepository;
    @Mock
    ModelMapper mockModelMapper ;
    @Mock
    CloudinaryService cloudinaryService;

    @BeforeEach
    public void setUp() {
        mockModelMapper = new ModelMapper();
        serviceTest = new NewsServiceImpl(mockModelMapper, mockNewsRepository, cloudinaryService);
    }

    @Test
    void findById() {

        NewsEntity newsEntity = new NewsEntity();
        newsEntity.setTitle("title")
                .setImageUrl("url")
                .setDescription("desc")
                .setAddedOn(LocalDateTime.now());

        NewsDetailsViewModel newsDetailsViewModel = this.mockModelMapper.map(newsEntity, NewsDetailsViewModel.class);

        Mockito.when(mockNewsRepository.findById(1L)).thenReturn(Optional.of(newsEntity));

        NewsDetailsViewModel byId = serviceTest.findById(1L);
        Assertions.assertEquals(newsDetailsViewModel.getTitle(), byId.getTitle());

    }
    @Test
    void findByAll() {

        NewsEntity newsEntity = new NewsEntity();
        newsEntity.setTitle("title")
                .setImageUrl("url")
                .setDescription("desc")
                .setAddedOn(LocalDateTime.now());
        NewsEntity newsEntity2 = new NewsEntity();
        newsEntity2.setTitle("title2")
                .setImageUrl("url")
                .setDescription("desc")
                .setAddedOn(LocalDateTime.now());

        List<NewsDetailsViewModel> newsDetailsViewModels = new ArrayList<>();
        newsDetailsViewModels.add(this.mockModelMapper.map(newsEntity, NewsDetailsViewModel.class));
        newsDetailsViewModels.add(this.mockModelMapper.map(newsEntity2, NewsDetailsViewModel.class));

        Mockito.when(mockNewsRepository.findAll()).thenReturn(List.of(newsEntity,newsEntity2));

        List<NewsViewModel> allNews = serviceTest.findAllNews();
        Assertions.assertEquals(2, allNews.size());

    }

}
