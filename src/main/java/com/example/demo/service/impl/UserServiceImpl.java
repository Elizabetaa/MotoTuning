package com.example.demo.service.impl;

import com.cloudinary.Cloudinary;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.entity.enums.RoleNameEnum;
import com.example.demo.model.service.UserRegisterServiceModel;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CloudinaryService;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final CloudinaryService cloudinaryService;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService, ModelMapper modelMapper, PasswordEncoder passwordEncoder, CloudinaryService cloudinaryService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.cloudinaryService = cloudinaryService;
    }



    @Override
    public void register(UserRegisterServiceModel map) {
        UserEntity user = this.modelMapper.map(map, UserEntity.class);
        user.setRoles(List.of(this.roleService.findByName(RoleNameEnum.USER)))
                .setPassword(this.passwordEncoder.encode(map.getPassword()));

        this.userRepository.save(user);
    }

    @Override
    public void addImage(MultipartFile imageUrl) throws IOException {
        MultipartFile img = imageUrl;
        String url =  cloudinaryService.uploadImage(img);
        UserEntity userEntity = this.userRepository.findById(1L).get();
        userEntity.setImageUrl(url);
        this.userRepository.save(userEntity);
    }

    @Override
    public UserEntity findByEmail(String authentication) {

        return this.userRepository.findByEmail(authentication).orElse(null);
    }


}
