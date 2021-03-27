package com.example.demo.services;

import com.example.demo.model.entity.RoleEntity;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.entity.enums.RoleNameEnum;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.impl.MotoTuningUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
class MotoTuningUserServiceTest {

    private MotoTuningUserService serviceTest;

    @Mock
    UserRepository mockUserRepository;

    @BeforeEach
    public void setUp() {
        serviceTest = new MotoTuningUserService(mockUserRepository);
    }

    @Test
    public void testUserNotFound() {
        Assertions.assertThrows(
                UsernameNotFoundException.class, () -> {
                    serviceTest.loadUserByUsername("userNotExist");
                });
    }

    @Test
    void testUserExist() {
        RoleEntity user = new RoleEntity();
        user.setRole(RoleNameEnum.USER);
        RoleEntity admin = new RoleEntity();
        admin.setRole(RoleNameEnum.ADMIN);

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("test@abv.bg").setPassword("password");
        userEntity.setRoles(List.of(user,admin));

        Mockito.when(mockUserRepository.findByEmail("test@abv.bg"))
                .thenReturn(Optional.of(userEntity));

        UserDetails userDetails = serviceTest.loadUserByUsername("test@abv.bg");

        Assertions.assertEquals(userEntity.getEmail(), userDetails.getUsername());
        Assertions.assertEquals(userEntity.getPassword(), userDetails.getPassword());
        Assertions.assertEquals(2, userDetails.getAuthorities().size());
        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        Assertions.assertTrue(roles.contains("ROLE_USER"));
        Assertions.assertTrue(roles.contains("ROLE_ADMIN"));
    }

}
