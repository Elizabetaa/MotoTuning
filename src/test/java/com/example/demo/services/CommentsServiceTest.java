package com.example.demo.services;

import com.example.demo.model.entity.BlogEntity;
import com.example.demo.model.entity.CommentEntity;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.service.CommentServiceModel;
import com.example.demo.model.view.BlogDetailsViewModel;
import com.example.demo.repository.CommentRepository;
import com.example.demo.service.BlogService;
import com.example.demo.service.UserService;
import com.example.demo.service.impl.BlogServiceImpl;
import com.example.demo.service.impl.CommentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
public class CommentsServiceTest {
    private CommentServiceImpl commentService;
    @Mock
    CommentRepository commentRepository;
    @Mock
    UserService userService;
    @Mock
    BlogService blogService;
    @Mock
    ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        modelMapper = new ModelMapper();
        commentService = new CommentServiceImpl(commentRepository,userService, blogService, modelMapper);
    }
//
//    @Test
//    void addComment(){
//        UserEntity userEntity = new UserEntity();
//        userEntity.setEmail("test@emal");
//
//        BlogDetailsViewModel blogEntity = new BlogDetailsViewModel();
//        blogEntity.setTitle("title");
//
//        CommentEntity commentEntity = new CommentEntity();
//        commentEntity.setComment("comment");
//        commentEntity.setUser(userEntity);
//
//        Mockito.when(userService.findByEmail("test@email")).thenReturn(userEntity);
//        Mockito.when(blogService.findById(1L)).thenReturn(blogEntity);
//        CommentServiceModel commentServiceModel = commentService.addComment("test@email","comment",1L);
//        System.out.println();
//    }

}
