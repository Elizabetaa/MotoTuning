package com.example.demo.service.impl;

import com.example.demo.model.entity.BlogEntity;
import com.example.demo.model.entity.CommentEntity;
import com.example.demo.model.service.CommentServiceModel;
import com.example.demo.repository.CommentRepository;
import com.example.demo.service.BlogService;
import com.example.demo.service.CommentService;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final BlogService blogService;
    private final ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository, UserService userService, BlogService blogService, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.blogService = blogService;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentServiceModel addComment(String comment, String email, Long id) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setComment(comment);
        commentEntity.setUser(this.userService.findByEmail(email));
        commentEntity.setBlog(this.modelMapper.map(this.blogService.findById(id), BlogEntity.class));
        this.commentRepository.save(commentEntity);

        return this.modelMapper.map(commentEntity,CommentServiceModel.class);
    }
}
