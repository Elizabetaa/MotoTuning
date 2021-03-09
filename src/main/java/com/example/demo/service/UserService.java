package com.example.demo.service;

import com.example.demo.model.entiry.RoleEntity;
import com.example.demo.model.entiry.enums.RoleNameEnum;
import com.example.demo.model.service.UserRegisterServiceModel;

public interface UserService {
    void initUsers();

    void register(UserRegisterServiceModel map);



}
