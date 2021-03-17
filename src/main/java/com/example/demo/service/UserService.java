package com.example.demo.service;

import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.service.UserRegisterServiceModel;

public interface UserService {


    void register(UserRegisterServiceModel map);


    UserEntity findByEmail(String authentication);
}
