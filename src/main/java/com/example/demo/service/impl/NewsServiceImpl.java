package com.example.demo.service.impl;

import com.example.demo.model.entity.NewsEntity;
import com.example.demo.model.service.AddNewsServiceModel;
import com.example.demo.repository.NewsRepository;
import com.example.demo.service.NewsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl implements NewsService {
    private final ModelMapper modelMapper;
    private final NewsRepository newsRepository;

    public NewsServiceImpl(ModelMapper modelMapper, NewsRepository newsRepository) {
        this.modelMapper = modelMapper;
        this.newsRepository = newsRepository;
    }

    @Override
    public void addNews(AddNewsServiceModel addNewsServiceModel) {
        NewsEntity newsEntity = this.modelMapper.map(addNewsServiceModel, NewsEntity.class);
        this.newsRepository.save(newsEntity);
    }
}
