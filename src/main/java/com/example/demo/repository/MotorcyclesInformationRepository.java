package com.example.demo.repository;

import com.example.demo.model.entity.MotorcyclesInformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotorcyclesInformationRepository extends JpaRepository<MotorcyclesInformationEntity, Long> {
}
