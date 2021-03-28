package com.example.demo.services;

import com.example.demo.model.entity.RoleEntity;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.entity.enums.RoleNameEnum;
import com.example.demo.model.service.EditAccountServiceModel;
import com.example.demo.model.service.UserRegisterServiceModel;
import com.example.demo.model.view.CurrentUserViewModel;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CloudinaryService;
import com.example.demo.service.RoleService;
import com.example.demo.service.impl.UserServiceImpl;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    private UserServiceImpl userService;
    @Mock
    UserRepository userRepository;
    @Mock
    RoleService roleService;
    @Mock
    ModelMapper modelMapper;
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    CloudinaryService cloudinaryService;

    @BeforeEach
    public void setUp() {
        userService = new UserServiceImpl(userRepository, roleService, modelMapper, passwordEncoder, cloudinaryService);
    }

    @Test
    void registerTest() {
        UserRegisterServiceModel userEntity = new UserRegisterServiceModel();
        userEntity.setEmail("test@").setFirstName("FirstName");

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRole(RoleNameEnum.USER);
        Mockito.when(roleService.findByName(RoleNameEnum.USER)).thenReturn(roleEntity);
        UserEntity map = new UserEntity();
        map.setEmail("test@").setFirstName("FirstName");
        Mockito.when(modelMapper.map(userEntity, UserEntity.class)).thenReturn(map);
        userService.register(userEntity);
        Mockito.verify(userRepository).save(map);
    }

    @Test
    void findByEmailTest() {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("test@").setFirstName("FirstName");

        Mockito.when(userRepository.findByEmail("test@")).thenReturn(Optional.of(userEntity));
        Assertions.assertEquals(userEntity, userService.findByEmail("test@"));
        Assertions.assertNull(userService.findByEmail("null"));
    }

    @Test
    void userNameExistsTest() {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("test@").setFirstName("FirstName");

        Mockito.when(userRepository.findByEmail("test@")).thenReturn(Optional.of(userEntity));

        Assertions.assertTrue(userService.userNameExists("test@"));
        Assertions.assertFalse(userService.userNameExists("false@"));
    }

    @Test
    void findCurrentUserTest() throws IOException {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("test@").setFirstName("FirstName");

        Mockito.when(userRepository.findByEmail("test@")).thenReturn(Optional.of(userEntity));
        CurrentUserViewModel map = new CurrentUserViewModel();
        map.setEmail("test@");
        map.setFirstName("FirstName");
        Mockito.when(modelMapper.map(userEntity, CurrentUserViewModel.class)).thenReturn(map);
        CurrentUserViewModel currentUser = userService.findCurrentUser("test@");

        Assertions.assertEquals(userEntity.getEmail(), currentUser.email);
        Assertions.assertEquals(userEntity.getFirstName(), currentUser.firstName);
    }

    @Test
    void editAccount() throws IOException {
        modelMapper = new ModelMapper();
        EditAccountServiceModel editAccountServiceModel = new EditAccountServiceModel();
        editAccountServiceModel.setFirstName("firstName");
        editAccountServiceModel.setLastName("lastName");
        editAccountServiceModel.setImageUrl(null);
        UserEntity userEntity = modelMapper.map(editAccountServiceModel, UserEntity.class);

        Mockito.when(userRepository.findByEmail("test@")).thenReturn(Optional.of(userEntity));
        userService.editAccount(editAccountServiceModel, "test@");

        Mockito.verify(userRepository).save(userEntity);
    }

    @Test
    void changeRoleTest() {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("test@").setFirstName("FirstName");
        RoleEntity admin = new RoleEntity();
        admin.setRole(RoleNameEnum.ADMIN).setId(1L);
        RoleEntity user = new RoleEntity();
        user.setRole(RoleNameEnum.USER).setId(1L);

        Mockito.when(userRepository.findByEmail("test@")).thenReturn(Optional.of(userEntity));
        Mockito.when(roleService.findByName(RoleNameEnum.ADMIN)).thenReturn(admin);
        Mockito.when(roleService.findByName(RoleNameEnum.USER)).thenReturn(user);

        userService.changeRole("test@", "admin");
        userService.changeRole("test@", "user");
        Mockito.verify(userRepository,Mockito.times(2)).save(userEntity);
    }

    @Test
    void getAllEmailsTest(){
        Mockito.when(userRepository.findAllEmails("test@abv.bg")).thenReturn(List.of("test1@abv.bg", "test2@abv.bg", "test3@abv.bg", "test4@abv.bg", "test5@abv.bg"));

        List<String> allEmails = userService.getAllEmails("test@abv.bg");
        Assertions.assertEquals(5,allEmails.size());
        Assertions.assertFalse(allEmails.contains("test@abv.bg"));
    }
}
