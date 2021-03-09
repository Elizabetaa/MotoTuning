package com.example.demo.service;

import com.example.demo.model.entiry.RoleEntity;
import com.example.demo.model.entiry.enums.RoleNameEnum;

public interface RoleService {
    void initRoles();
    RoleEntity findByName(RoleNameEnum roleNameEnum);
}
