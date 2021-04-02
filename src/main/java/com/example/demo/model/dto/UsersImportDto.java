package com.example.demo.model.dto;

import com.example.demo.model.binding.UserRegisterBindingModel;
import com.example.demo.model.entity.RoleEntity;
import com.google.gson.annotations.Expose;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

public class UsersImportDto {
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private String email;
    @Expose
    private String password;
    @Expose
    private String imageUrl;


    public UsersImportDto() {
    }

    @NotBlank
    @Size(min = 2, message = "First name length must be at least 2 symbols")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotBlank
    @Size(min = 2, message = "Last name length must be at least 2 symbols")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotBlank
    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotBlank
    @Size(min = 6, message = "Password length must be at least 2 symbols")
    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }
    @NotBlank
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
