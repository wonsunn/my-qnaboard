package com.qnaboard.myqnaboard.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class User extends AbstractEntity {

    @Column(nullable=false, length=20, unique=true)
    @JsonProperty
    private String userId;

    @JsonIgnore
    private String password;

    @JsonProperty
    private String name;

    @JsonProperty
    private String email;

    public boolean matchId(Long newId) {
        return getId().equals(newId);
    }

    public String getUserId() {
        return userId;
    }

    public boolean matchPassword(String newPassword) {
        return password.equals(newPassword);
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void update(User newUser) {
        this.password = newUser.password;
        this.name = newUser.name;
        this.email = newUser.email;
    }

    @Override
    public String toString() {
        return "User[" + super.toString() +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                " email='" + email + '\'' +
                ']';
    }
}
