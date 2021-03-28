package com.example.demo.service.impl;

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
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.modelmapper.ModelMapper;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final CloudinaryService cloudinaryService;

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

        if (role.equals("admin")){
            userEntity.setRoles(List.of(admin,user));
        }else {
            userEntity.setRoles(List.of(user));
        }

        this.userRepository.save(userEntity);
    }


}
