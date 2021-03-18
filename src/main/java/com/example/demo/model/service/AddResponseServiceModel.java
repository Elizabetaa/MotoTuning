package com.example.demo.model.service;

public class AddResponseServiceModel {
    private Long id;
    private String response;

    public AddResponseServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public AddResponseServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getResponse() {
        return response;
    }

    public AddResponseServiceModel setResponse(String response) {
        this.response = response;
        return this;
    }
}
