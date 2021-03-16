package com.example.demo.service;

import com.example.demo.model.service.UserRegisterServiceModel;

public interface UserService {
    void initUsers();

    void register(UserRegisterServiceModel map);



}
