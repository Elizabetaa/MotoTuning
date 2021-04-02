package com.example.demo.service.impl;

import com.example.demo.config.util.ValidationUtil;
import com.example.demo.model.dto.NewsImportDto;
import com.example.demo.model.entity.NewsEntity;
import com.example.demo.model.service.AddNewsServiceModel;
import com.example.demo.model.view.NewsDetailsViewModel;
import com.example.demo.model.view.NewsViewModel;
import com.example.demo.repository.NewsRepository;
import com.example.demo.service.CloudinaryService;
import com.example.demo.service.NewsService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class NewsServiceImpl implements NewsService {
    private final ModelMapper modelMapper;
    private final NewsRepository newsRepository;
    private final CloudinaryService cloudinaryService;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    @Autowired
    private  Gson gson;
    @Autowired
    private  ValidationUtil validationUtil;

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
            NewsViewModel newsViewModel = this.modelMapper.map(n, NewsViewModel.class);
            newsViewModel.setAddedOn(n.getAddedOn().format(formatter));
            newsViewModels.add(newsViewModel);
        });
        return newsViewModels;
    }

    @Override
    public NewsDetailsViewModel findById(Long id) {
        NewsEntity newsEntity = this.newsRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Don't existing news with id " + id));

        NewsDetailsViewModel newsDetailsViewModel = this.modelMapper.map(newsEntity, NewsDetailsViewModel.class);
        newsDetailsViewModel.setAddedOn(newsEntity.getAddedOn().format(formatter));
        return newsDetailsViewModel;
    }

    public String readPassengersFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of("src/main/resources/static/files/news.json")));
    }

    @Override
    public void initNews() throws IOException {
        if (newsRepository.count() == 0) {
            NewsImportDto[] newsImportDto = this.gson.fromJson(readPassengersFileContent(), NewsImportDto[].class);
            for (NewsImportDto importDto : newsImportDto) {
                if (validationUtil.isValid(importDto)) {
                    NewsEntity newsEntity = this.modelMapper.map(importDto, NewsEntity.class);
                    this.newsRepository.save(newsEntity);
                }
            }
        }
    }


}

