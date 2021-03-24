package com.example.demo.model.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class CommentEntity extends BaseEntity{

    private String comment;
    private BlogEntity blog;
    private UserEntity user;

    public CommentEntity() {
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @ManyToOne()
    public BlogEntity getBlog() {
        return blog;
    }

    public void setBlog(BlogEntity blogEntity) {
        this.blog = blogEntity;
    }

    @ManyToOne()
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity userEntity) {
        this.user = userEntity;
    }
}
