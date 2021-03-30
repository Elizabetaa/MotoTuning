package com.example.demo.repository;

import com.example.demo.model.entity.InquiryEntity;
import com.example.demo.model.entity.enums.InquiryTypeNameEnum;
import com.example.demo.model.view.InquiryTaskViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@RequestMapping
public interface InquiryRepository extends JpaRepository<InquiryEntity, Long> {
    Optional<List<InquiryEntity>> findByInquiryAndResponse(InquiryTypeNameEnum inquiry, String response);
    List<InquiryEntity> findByEmailOrderByResponseDesc(String email);
    List<InquiryEntity> findAllByAgree(boolean agree);
}
