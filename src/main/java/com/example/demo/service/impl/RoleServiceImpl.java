package com.example.demo.service.impl;

import com.example.demo.model.entity.RoleEntity;
import com.example.demo.model.entity.enums.RoleNameEnum;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void initRoles() {

        if (roleRepository.count() == 0) {
            Arrays.stream(RoleNameEnum.values()).forEach(r -> {
                RoleEntity roleEntity = new RoleEntity();
                roleEntity.setRole(r);
                this.roleRepository.save(roleEntity);
            });
        }

    }

    @Override
    public RoleEntity findByName(RoleNameEnum roleNameEnum) {
        return this.roleRepository.findByRole(roleNameEnum).orElse(null);
    }
}
