package com.example.demo.model.entity;


import com.example.demo.model.entity.enums.BlogCategoryNameEnum;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "blogs")
public class BlogEntity extends BaseEntity {
    private String title;
    private BlogCategoryNameEnum blogCategory;
    private String imageUrl;
    private String description;
    private UserEntity author;
    private LocalDateTime addedOn;
    private List<CommentEntity> commentEntities;

    public BlogEntity() {
    }

    @Column(nullable = false)
    public String getTitle() {
        return title;
    }

    public BlogEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    @Column(nullable = false)
    public BlogCategoryNameEnum getBlogCategory() {
        return blogCategory;
    }

    public void setBlogCategory(BlogCategoryNameEnum blogCategory) {
        this.blogCategory = blogCategory;
    }

    @Column(nullable = false)
    public String getImageUrl() {
        return imageUrl;
    }

    public BlogEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    @Column(nullable = false,columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public BlogEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    @ManyToOne
    public UserEntity getAuthor() {
        return author;
    }

    public BlogEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    @Column(nullable = false)
    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public BlogEntity setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
        return this;
    }

    @OneToMany(mappedBy = "blog",fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    public List<CommentEntity> getComments() {
        return commentEntities;
    }

    public void setComments(List<CommentEntity> commentEntities) {
        this.commentEntities = commentEntities;
    }
}
