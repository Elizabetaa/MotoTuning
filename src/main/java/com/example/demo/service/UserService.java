package com.example.demo.service;

import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.service.UserRegisterServiceModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

public interface UserService {


    void register(UserRegisterServiceModel map);
    void addImage(MultipartFile imageUrl) throws IOException;

    UserEntity findByEmail(String authentication);

}
