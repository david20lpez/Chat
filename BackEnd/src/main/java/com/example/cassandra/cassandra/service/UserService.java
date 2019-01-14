/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.cassandra.cassandra.service;

import com.example.cassandra.cassandra.model.User;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author LENOVO
 */
public interface UserService {
    List<User> getAllUsers();
    User postuser(User user);
    User updateUser(UUID id, User user);
    void deleteUser(UUID id);
    List<User> findByActive (boolean active);
    List<User> findByCategory(String category);
    User logIn(User user);
    User logOut(User user);
}
