package com.example.demo.service.impl;

import com.example.demo.config.util.ValidationUtil;
import com.example.demo.model.dto.UsersImportDto;
import com.example.demo.model.entity.NewsEntity;
import com.example.demo.model.entity.RoleEntity;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.entity.enums.RoleNameEnum;
import com.example.demo.model.service.EditAccountServiceModel;
import com.example.demo.model.service.UserRegisterServiceModel;
import com.example.demo.model.view.CurrentUserViewModel;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CloudinaryService;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final CloudinaryService cloudinaryService;
    @Autowired
    private  Gson gson;
    @Autowired
    private  ValidationUtil validationUtil;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService, ModelMapper modelMapper, PasswordEncoder passwordEncoder,
                           CloudinaryService cloudinaryService) {
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

        user.setImageUrl("https://res.cloudinary.com/elizabetak/image/upload/v1616422750/default-user-Img_dpqzkv.png");
        this.userRepository.save(user);
    }


    @Override
    public UserEntity findByEmail(String authentication) {
        return this.userRepository.findByEmail(authentication).orElse(null);
    }

    @Override
    public boolean userNameExists(String email) {
        return this.userRepository.findByEmail(email).isPresent();
    }

    @Override
    public void editAccount(EditAccountServiceModel editAccountServiceModel, String email) throws IOException {
        UserEntity userEntity = this.userRepository.findByEmail(email).get();

        if (editAccountServiceModel.getImageUrl() == null || !editAccountServiceModel.getImageUrl().isEmpty()) {
            MultipartFile img = editAccountServiceModel.getImageUrl();
            String url = cloudinaryService.uploadImage(img);
            userEntity.setImageUrl(url);

        }

        userEntity.setFirstName(editAccountServiceModel.getFirstName())
                .setLastName(editAccountServiceModel.getLastName());

        this.userRepository.save(userEntity);
    }

    @Override
    public CurrentUserViewModel findCurrentUser(String email) throws IOException {
        UserEntity userEntity = this.userRepository.findByEmail(email).get();
        CurrentUserViewModel map = this.modelMapper.map(userEntity, CurrentUserViewModel.class);
        return this.modelMapper.map(userEntity, CurrentUserViewModel.class);
    }

    @Override
    public List<String> getAllEmails(String email) {
        return this.userRepository.findAllEmails(email);

    }


    @Override
    public void changeRole(String email, String role) {
        UserEntity userEntity = this.userRepository.findByEmail(email).get();
        RoleEntity admin = roleService.findByName(RoleNameEnum.ADMIN);
        RoleEntity user = roleService.findByName(RoleNameEnum.USER);

        if (role.equals("admin")) {
            userEntity.setRoles(List.of(admin, user));
        } else {
            userEntity.setRoles(List.of(user));
        }
        System.out.println();
        this.userRepository.save(userEntity);
    }
    public String readPassengersFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of("src/main/resources/static/files/users.json")));
    }


    @Override
    public void initUsers() throws IOException {
        if (userRepository.count() == 0) {
            UsersImportDto[] usersImportDto = this.gson.fromJson(readPassengersFileContent(), UsersImportDto[].class);
            for (UsersImportDto importDto : usersImportDto) {
                if (validationUtil.isValid(importDto)) {
                    UserEntity userEntity = this.modelMapper.map(importDto, UserEntity.class);
                    userEntity.setPassword(this.passwordEncoder.encode(importDto.getPassword()));
                    if (userRepository.count()==0){
                        userEntity.setRoles(List.of(roleService.findByName(RoleNameEnum.USER),roleService.findByName(RoleNameEnum.ADMIN)));
                    }else {
                        userEntity.setRoles(List.of(roleService.findByName(RoleNameEnum.USER)));
                    }
                    this.userRepository.save(userEntity);
                }
            }
        }
    }


}
