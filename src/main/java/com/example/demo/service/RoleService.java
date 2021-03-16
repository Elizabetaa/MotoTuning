package com.example.demo.service;

import com.example.demo.model.entity.RoleEntity;
import com.example.demo.model.entity.enums.RoleNameEnum;

public interface RoleService {
    void initRoles();
    RoleEntity findByName(RoleNameEnum roleNameEnum);
}
