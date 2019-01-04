/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.cassandra.cassandra.controller;

import com.datastax.driver.core.utils.UUIDs;
import com.example.cassandra.cassandra.model.User;
import com.example.cassandra.cassandra.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author LENOVO
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserRepository userRepository;
    
    @GetMapping("/users")
    public List<User> getAllUsers(){
        System.out.println("This is a change");
        return userRepository.findAll();
    }
    
    @PostMapping("users/create")
    public ResponseEntity<User> postUser(@RequestBody User user){
        System.out.println("Create user: " + user.getName() + "...");
        
        user.setId(UUIDs.timeBased());
        User _user = userRepository.save(user);
        return new ResponseEntity<>(_user, HttpStatus.OK);
    }
    
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") UUID id, @RequestBody User user){
        System.out.println("Update User with ID = " + id + "...");
        
        Optional<User> userData = userRepository.findById(id);
        if(userData.isPresent()){
            User _user = userData.get();
            _user.setName(user.getName());
            _user.setLastname(user.getLastname());
            _user.setActive(user.isActive());
            return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") UUID id){
        System.out.println("Delete user with ID: " + id + "..." );
        
        userRepository.deleteById(id);
        return new ResponseEntity<>("User has been deleted", HttpStatus.OK);
    }
    
    @GetMapping("users/active/{active}")
    public List<User> findByActive(@PathVariable boolean active){
        List<User> users = userRepository.findByActive(active);
        return users;
    }
    
}
