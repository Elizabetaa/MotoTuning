package com.example.demo.services;

import com.example.demo.model.entity.RoleEntity;
import com.example.demo.model.entity.enums.RoleNameEnum;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.impl.RoleServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {
    private RoleServiceImpl roleService;

    @Mock
    RoleRepository roleRepository;

    @BeforeEach
    public void setUp(){
        roleService = new RoleServiceImpl(roleRepository);
    }

    @Test
    void findByName (){
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRole(RoleNameEnum.USER);

        Mockito.when(roleRepository.findByRole(RoleNameEnum.USER)).thenReturn(Optional.of(roleEntity));

        Assertions.assertEquals(roleEntity,roleService.findByName(RoleNameEnum.USER));
    }

    @Test
    void initRolesWhenCountIsBiggerThanZero (){
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRole(RoleNameEnum.USER);

        roleService.initRoles();
        Mockito.verify(roleRepository, Mockito.times(0)).save(roleEntity);


    }
}
