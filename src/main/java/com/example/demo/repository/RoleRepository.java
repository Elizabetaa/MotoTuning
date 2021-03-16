package com.example.demo.repository;

import com.example.demo.model.entity.RoleEntity;
import com.example.demo.model.entity.enums.RoleNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByRole(RoleNameEnum roleNameEnum);
}
