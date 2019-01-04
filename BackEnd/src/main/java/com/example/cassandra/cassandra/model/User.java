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
    private String lastname;
    private boolean active;

    public User() {
    }

    public User(UUID id, String name, String lastname, boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.lastname = lastname;
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    
    
    @Override
    public String toString(){
        return "User = [id " + id + ", name " + name + ", active" + active + " ]";
    }
    
}
