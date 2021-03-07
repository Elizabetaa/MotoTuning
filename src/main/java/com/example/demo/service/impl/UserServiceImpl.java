package com.example.demo.service.impl;

import com.example.demo.model.entiry.RoleEntity;
import com.example.demo.model.entiry.UserEntity;
import com.example.demo.model.entiry.enums.RoleNameEnum;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void initUsers() {
        if (userRepository.count()==0){
            RoleEntity admin = new RoleEntity().setRole(RoleNameEnum.ADMIN);
            RoleEntity user = new RoleEntity().setRole(RoleNameEnum.USER);
            roleRepository.saveAll(List.of(admin,user));
            UserEntity userEntity = new UserEntity()
                    .setEmail("elizabeta@abv.bg")
                    .setFirstName("Elizabeta")
                    .setLastName("Koleva")
                    .setPassword("123456")
                    .setRoles(List.of(admin,user));
            userRepository.save(userEntity);
        }

    }
}
