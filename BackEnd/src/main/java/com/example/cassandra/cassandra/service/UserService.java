/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.cassandra.cassandra.service;

import dto.UserDTO;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author LENOVO
 */
public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO postUser(UserDTO userDTO);
    UserDTO updateUser(UUID id, UserDTO userDTO);
    UserDTO selectUser(UUID id);
    UserDTO deleteUser(UUID id);
    List<UserDTO> findByActive (boolean active);
    List<UserDTO> findByCategory(String category);
    List<UserDTO> findByCategoryAndActive(String category);
    UserDTO logIn(UserDTO userDTO);
    UserDTO logOut(UserDTO userDTO);
}
