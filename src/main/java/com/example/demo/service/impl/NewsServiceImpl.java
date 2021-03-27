package com.example.demo.service.impl;

import com.example.demo.model.entity.NewsEntity;
import com.example.demo.model.service.AddNewsServiceModel;
import com.example.demo.model.view.NewsDetailsViewModel;
import com.example.demo.model.view.NewsViewModel;
import com.example.demo.repository.NewsRepository;
import com.example.demo.service.CloudinaryService;
import com.example.demo.service.NewsService;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class NewsServiceImpl implements NewsService {
    private final ModelMapper modelMapper;
    private final NewsRepository newsRepository;
    private final CloudinaryService cloudinaryService;

    public NewsServiceImpl(ModelMapper modelMapper, NewsRepository newsRepository, CloudinaryService cloudinaryService) {
        this.modelMapper = modelMapper;
        this.newsRepository = newsRepository;
        this.cloudinaryService = cloudinaryService;
    }



    @Override
    @CacheEvict(value = "news", allEntries = true)
    public void addNews(AddNewsServiceModel addNewsServiceModel) throws IOException {
        NewsEntity newsEntity = this.modelMapper.map(addNewsServiceModel, NewsEntity.class);
            MultipartFile img = addNewsServiceModel.getImageUrl();
            String url = cloudinaryService.uploadImage(img);
            newsEntity.setImageUrl(url);
            this.newsRepository.save(newsEntity);
    }

    @Override
    @Cacheable("news")
    public List<NewsViewModel> findAllNews() {
        List<NewsViewModel> newsViewModels = new ArrayList<>();
        this.newsRepository.findAll().forEach(n -> {
            newsViewModels.add(this.modelMapper.map(n, NewsViewModel.class));
        });
        return newsViewModels;
    }

    @Override
    public NewsDetailsViewModel findById(Long id) {
        NewsEntity newsEntity = this.newsRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Don't existing news with id " + id));
        return this.modelMapper.map(newsEntity,NewsDetailsViewModel.class);
    }
}
