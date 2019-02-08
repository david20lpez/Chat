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
import dto.UserDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LENOVO
 */
@Service
public class UserManagementService implements UserService{
   
    private UserRepository userRepository;
    private ModelMapper modMapper;
    
    public UserManagementService(UserRepository userRepository, ModelMapper modMapper){
        this.userRepository = userRepository;
        this.modMapper = modMapper;
    }
    
    @Override
    public List<UserDTO> getAllUsers(){
        List<User> users = userRepository.findAll();
        List<UserDTO> usersDTO = new ArrayList<>();
        for(User user : users){
            usersDTO.add(modMapper.map(user, UserDTO.class));
        }
        return usersDTO;
    }
    
    @Override
    public UserDTO postUser(UserDTO userDTO){
        userDTO.setId(UUIDs.timeBased());
        User user = userRepository.save(modMapper.map(userDTO, User.class));
        return modMapper.map(user, UserDTO.class);
    }
    
    @Override
    public UserDTO updateUser(UUID id, UserDTO userDTO){
        Optional<User> userData = userRepository.findById(id);
        User user = new User();
        if(userData.isPresent()){
            UserDTO userDTO1 = modMapper.map(userData.get(), UserDTO.class);
            userDTO1.setId(userDTO.getId());
            userDTO1.setName(userDTO.getName());
            userDTO1.setCategory(userDTO.getCategory());
            userDTO1.setActive(userDTO.isActive());
            userDTO1.setRole(userDTO.getPassword());
            userDTO1.setEmail(userDTO.getEmail());
            user = userRepository.save(modMapper.map(userDTO1, User.class));
        }
        return modMapper.map(user, UserDTO.class);
    }
    
    @Override
    public UserDTO selectUser(UUID id){
        Optional<User> userOptional = userRepository.findById(id);
        return modMapper.map(userOptional.get(), UserDTO.class);
    }
    
    @Override
    public UserDTO deleteUser(UUID id){
       UserDTO user = selectUser(id);
       userRepository.deleteById(id);
       return user;
    }
    
    @Override
    public List<UserDTO> findByActive(boolean active){
        List<User> users = userRepository.findByActive(active);
        List<UserDTO> usersDTO = new ArrayList<>();
        for(User user : users){
            usersDTO.add(modMapper.map(user, UserDTO.class));
        }
        return usersDTO;
    }
    
    @Override
    public List<UserDTO> findByCategory(String category){
        List<User> users = userRepository.findByCategory(category);
        List<UserDTO> usersDTO = new ArrayList<>();
        for(User user : users){
            usersDTO.add(modMapper.map(user, UserDTO.class));
        }
        return usersDTO;
    }
    
    @Override
    public List<UserDTO> findByCategoryAndActive(String category){
        List<User> users = userRepository.findByCategory(category);
        List<UserDTO> usersDTO = new ArrayList<>();
        for(User user : users){
            if(user.isActive()){
                usersDTO.add(modMapper.map(user, UserDTO.class));
            }
        }
        return usersDTO;
    }
    
    @Override 
    public UserDTO logIn(UserDTO userDTO){
        Optional<User> user = userRepository.findByEmailAndPassword(userDTO.getEmail(), userDTO.getPassword());
        User user1 = new User();
        if (user.isPresent()) {
            userDTO = modMapper.map(user.get(), UserDTO.class);
            userDTO.setActive(true);
            user1 = userRepository.save(modMapper.map(userDTO, User.class));
            return modMapper.map(user1, UserDTO.class);
        }
        return null;
    }
    
    @Override
    public UserDTO logOut(UserDTO userDTO){
        UUID id = userDTO.getId();
        Optional<User> user = userRepository.findById(id);
        User user1 = new User();
        if(user.isPresent()){
            userDTO = modMapper.map(user.get(), UserDTO.class);
            userDTO.setActive(false);
            user1 = userRepository.save(modMapper.map(userDTO, User.class));
            return modMapper.map(user1, UserDTO.class);  
        }
        return null;
    }

}
    

