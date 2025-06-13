package com.example.tecnoMeet.domain;

import com.example.tecnoMeet.utilities.InvalidDataException;

import java.time.LocalDateTime;
import java.util.UUID;

public class User {
    private String id = UUID.randomUUID().toString();
    private String name;
    private String email;
    private String bio;
    private String photo;
    private boolean deleted = false;
    private LocalDateTime createdAt = LocalDateTime.now();

    public User() {}

    public User(String name, String email, String bio, String photo) {
        setName(name);
        setEmail(email);
        this.bio = bio;
        this.photo = photo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new InvalidDataException("Name is required");
        }
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new InvalidDataException("Email cannot be empty");
        }
        if (!email.contains("tecnocampus")) {
            throw new InvalidDataException("Email must be a Tecnocampus address");
        }
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
