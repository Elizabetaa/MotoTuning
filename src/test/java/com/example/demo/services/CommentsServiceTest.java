package com.example.demo.services;

import com.example.demo.model.entity.BlogEntity;
import com.example.demo.model.entity.CommentEntity;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.service.CommentServiceModel;
import com.example.demo.model.view.BlogDetailsViewModel;
import com.example.demo.repository.BlogRepository;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.UserRepository;
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

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CommentsServiceTest {
    private CommentServiceImpl commentService;
    @Mock
    CommentRepository commentRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    BlogRepository blogRepository;

    @Mock
    UserService userService;
    @Mock
    BlogService blogService;
    @Mock
    ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        modelMapper = new ModelMapper();
        commentService = new CommentServiceImpl(commentRepository, userService, blogService, modelMapper);
    }

//    @Test
//    void addComment() {
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
//        Mockito.when(userRepository.findByEmail("test@email")).thenReturn(Optional.of(userEntity));
//        BlogEntity blogEntity1 = this.modelMapper.map(blogEntity,BlogEntity.class);
//        Mockito.when(blogRepository.findById(1L)).thenReturn(Optional.of(modelMapper.map(blogEntity,BlogEntity.class)));
//        CommentServiceModel commentServiceModel = commentService.addComment("test@email", "comment", 1L);
//        System.out.println();
//    }

}
