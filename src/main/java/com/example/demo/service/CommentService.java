package com.example.demo.service;

import com.example.demo.model.service.CommentServiceModel;

public interface CommentService {
    CommentServiceModel addComment(String comment, String email, Long id);

    CommentServiceModel addCommentToNews(String comment, String name, Long id);
}
