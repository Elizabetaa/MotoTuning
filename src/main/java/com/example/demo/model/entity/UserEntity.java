package com.example.demo.model.entity;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String imageUrl;
    private List<RoleEntity> roles;
    private List<BlogEntity> blogs;
    private List<InquiryEntity> inquiries;

    public UserEntity() {
    }

    @Column(nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public UserEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @Column(nullable = false)
    public String getLastName() {
        return lastName;
    }

    public UserEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @Column(nullable = false, unique = true)
    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public UserEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    public List<RoleEntity> getRoles() {
        return roles;
    }

    public UserEntity setRoles(List<RoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    @OneToMany(mappedBy = "author")
    public List<BlogEntity> getBlogs() {
        return blogs;
    }

    public UserEntity setBlogs(List<BlogEntity> blogs) {
        this.blogs = blogs;
        return this;
    }



    @OneToMany(mappedBy = "author")
    public List<InquiryEntity> getInquiries() {
        return inquiries;
    }

    public UserEntity setInquiries(List<InquiryEntity> inquiries) {
        this.inquiries = inquiries;
        return this;
    }


}
