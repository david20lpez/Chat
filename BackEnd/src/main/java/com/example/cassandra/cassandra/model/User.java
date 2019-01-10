/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.cassandra.cassandra.model;

import java.util.UUID;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

/**
 *
 * @author LENOVO
 */
@Table
public class User {
    
    @PrimaryKey
    private UUID id;
    private String name;
    private String email;
    private boolean active;
    private String category;
    private String password;

    public User() {
    }

    public User(UUID id, String name, String email, boolean active, String category, String password) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.email = email;
        this.category = category;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    @Override
    public String toString(){
        return "User = [id " + id + ", name " + name + ", active" + active + " ]";
    }
    
}
