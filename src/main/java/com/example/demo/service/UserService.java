package com.example.demo.service;

import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.service.EditAccountServiceModel;
import com.example.demo.model.service.UserRegisterServiceModel;
import com.example.demo.model.view.CurrentUserViewModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {


    void register(UserRegisterServiceModel map);
    void addImage(MultipartFile imageUrl) throws IOException;

    UserEntity findByEmail(String authentication);

    boolean userNameExists(String email);

    void editAccount(EditAccountServiceModel editAccountServiceModel, String name) throws IOException;

    CurrentUserViewModel findCurrentUser(String name) throws IOException;
}
