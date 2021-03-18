package com.example.demo.model.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AddResponseBindingModel {
    private Long id;
    private String response;

    public AddResponseBindingModel() {
    }

    @NotBlank
    @Size(min = 10,message = "Response can not be too short!")
    public String getResponse() {
        return response;
    }

    public AddResponseBindingModel setResponse(String response) {
        this.response = response;
        return this;
    }

    public Long getId() {
        return id;
    }

    public AddResponseBindingModel setId(Long id) {
        this.id = id;
        return this;
    }
}
