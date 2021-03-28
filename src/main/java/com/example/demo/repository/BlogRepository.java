package com.example.demo.repository;

import com.example.demo.model.entity.BlogEntity;
import com.example.demo.model.entity.enums.BlogCategoryNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<BlogEntity, Long> {
    List<BlogEntity> findTop4ByOrderByAddedOnDesc();

    List<BlogEntity> findByBlogCategory(BlogCategoryNameEnum blogCategory);
}
