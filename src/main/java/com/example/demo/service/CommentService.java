package com.example.demo.service;

import com.example.demo.model.service.CommentServiceModel;

public interface CommentService {
    CommentServiceModel addComment(String comment, String email, Long id);
}
