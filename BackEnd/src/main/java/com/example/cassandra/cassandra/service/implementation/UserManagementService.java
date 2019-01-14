/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.cassandra.cassandra.service.implementation;

import com.datastax.driver.core.utils.UUIDs;
import com.example.cassandra.cassandra.model.User;
import com.example.cassandra.cassandra.repository.UserRepository;
import com.example.cassandra.cassandra.service.UserService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LENOVO
 */
@Service
public class UserManagementService implements UserService{
    @Autowired
    UserRepository userRepository;
    
    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    
    @Override
    public User postuser(User user){
        user.setId(UUIDs.timeBased());
        User user1 = userRepository.save(user);
        return user1;
    }
    
    @Override
    public User updateUser(UUID id, User user){
        Optional<User> userData = userRepository.findById(id);
        if(userData.isPresent()){
            User user1 = userData.get();
            user1.setName(user.getName());
            user1.setEmail(user.getEmail());
            user1.setActive(user.isActive());
            return user1;
        }
        return user;
    }
    
    @Override
    public void deleteUser(UUID id){
       userRepository.deleteById(id);
    }
    
    @Override
    public List<User> findByActive(boolean active){
        return userRepository.findByActive(active);
    }
    
    @Override
    public List<User> findByCategory(String category){
        return userRepository.findByCategory(category);
    }
    
    @Override 
    public User logIn(User user){
        Optional<User> user1 = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if (user1.isPresent()) {
            user1.get().setActive(true);
            userRepository.save(user1.get());
            return user1.get();
        }
        return null;
    }
    
    @Override
    public User logOut(User user){
        UUID id = user.getId();
        Optional<User> user1 = userRepository.findById(id);
        if(user1.isPresent()){
            user1.get().setActive(false);
            userRepository.save(user1.get());
            return user1.get();
        }
        return null;
    }
        
}
    

