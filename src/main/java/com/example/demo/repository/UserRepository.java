package com.example.demo.repository;

import com.example.demo.model.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{

    Optional<UserEntity> findByEmail(String email);

    @Query("select u.email from UserEntity as u WHERE u.email not in :email")
    List<String> findAllEmails(String email);


}
