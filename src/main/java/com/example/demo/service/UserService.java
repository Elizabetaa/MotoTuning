package com.example.demo.service;

import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.service.EditAccountServiceModel;
import com.example.demo.model.service.UserRegisterServiceModel;
import com.example.demo.model.view.CurrentUserViewModel;


import java.io.IOException;
import java.util.List;

public interface UserService {


    void register(UserRegisterServiceModel map);

    UserEntity findByEmail(String authentication);

    boolean userNameExists(String email);

    void editAccount(EditAccountServiceModel editAccountServiceModel, String name) throws IOException;

    CurrentUserViewModel findCurrentUser(String name) throws IOException;

    List<String> getAllEmails(String email);


    void changeRole(String email, String role);

    void initUsers() throws IOException;
}
