package com.example.demo.repository;

import com.example.demo.model.entity.InquiryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public interface InquiryRepository extends JpaRepository<InquiryEntity, Long> {
}
