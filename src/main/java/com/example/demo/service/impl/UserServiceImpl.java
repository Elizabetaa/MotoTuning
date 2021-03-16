package com.example.demo.service.impl;

import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.entity.enums.RoleNameEnum;
import com.example.demo.model.service.UserRegisterServiceModel;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void initUsers() {
        if (userRepository.count() == 0) {
            UserEntity userEntity = new UserEntity()
                    .setEmail("elizabeta@abv.bg")
                    .setFirstName("Elizabeta")
                    .setLastName("Koleva")
                    .setPassword("123456")
                    .setRoles(List.of(this.roleService.findByName(RoleNameEnum.ADMIN), this.roleService.findByName(RoleNameEnum.USER)));
            userRepository.save(userEntity);
        }

    }

    @Override
    public void register(UserRegisterServiceModel map) {
        UserEntity user = this.modelMapper.map(map, UserEntity.class);
        user.setRoles(List.of(this.roleService.findByName(RoleNameEnum.USER)))
                .setPassword(this.passwordEncoder.encode(map.getPassword()));

        this.userRepository.save(user);
    }


}
