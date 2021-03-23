package com.example.demo.service;

import com.example.demo.model.service.AddNewsServiceModel;
import com.example.demo.model.view.NewsDetailsViewModel;
import com.example.demo.model.view.NewsViewModel;

import java.io.IOException;
import java.util.List;

public interface NewsService {
    void addNews(AddNewsServiceModel addNewsServiceModel) throws IOException;

    List<NewsViewModel> findAllNews();

    NewsDetailsViewModel findById(Long id);
}
