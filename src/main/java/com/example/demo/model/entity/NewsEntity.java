package com.example.demo.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "news")
public class NewsEntity extends BaseEntity{
    private String title;
    private String imageUrl;
    private String description;
    private LocalDateTime addedOn;

    public NewsEntity() {
    }
    @Column(nullable = false)
    public String getTitle() {
        return title;
    }

    public NewsEntity setTitle(String title) {
        this.title = title;
        return this;
    }
    @Column(nullable = false)
    public String getImageUrl() {
        return imageUrl;
    }

    public NewsEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
    @Column(nullable = false,columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public NewsEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    @Column(nullable = false)
    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
    }
}
