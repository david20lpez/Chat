/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.cassandra.cassandra.controller;

import com.example.cassandra.cassandra.service.implementation.UserManagementService;
import dto.UserDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author LENOVO
 */
public class UserControllerTest {
    
    private UserManagementService userService;
    private UserController userController;
    
    public UserControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        userService = Mockito.mock(UserManagementService.class);
        userController = new UserController(userService);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllUsers method, of class UserController.
     */
    @Test
    public void getUsers_Test(){
        List<UserDTO> users = new ArrayList<>();
        UserDTO user = new UserDTO(UUID.randomUUID(), "kev", "kt@gmail.com", false, "Swimming", "sjkandsa", "admin");
        users.add(user);
        when(userService.getAllUsers()).thenReturn(users);
        List<UserDTO> result = userController.getAllUsers();
        assertNotNull(result);
    }
    
    @Test
    public void addUser_Test(){
        UserDTO user = new UserDTO(UUID.randomUUID(), "kev", "kt@gmail.com", false, "Swimming", "sjkandsa", "admin");
        when(userService.postUser(user)).thenReturn(user);
        ResponseEntity <?> result = userController.postUser(user);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
    
    public void updateUser_Test(){
        UUID id = UUID.randomUUID();
        UserDTO user = new UserDTO();
        UserDTO user2 = new UserDTO();
        when(userService.updateUser(id, user)).thenReturn(user2);
        ResponseEntity <?> result = userController.updateUser(id, user);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
    
    public void deleteUser_Test(){
        UUID id = UUID.randomUUID();
        UserDTO user = new UserDTO();
        user.setId(id);
        when(userService.deleteUser(id)).thenReturn(user);
        ResponseEntity <?> result = userController.deleteUser(id);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
    
    @Test
    public void getActiveUsers_Test(){
        List<UserDTO> users = new ArrayList<>();
        UserDTO user = new UserDTO(UUID.randomUUID(), "kev", "kt@gmail.com", true, "Swimming", "sjkandsa", "admin");
        users.add(user);
        when(userService.findByActive(true)).thenReturn(users);
        List<UserDTO> result = userController.findByActive(true);
        assertNotNull(result);
    }
    
    @Test
    public void getUsersByCategory_Test(){
        List<UserDTO> users = new ArrayList<>();
        UserDTO user = new UserDTO(UUID.randomUUID(), "kev", "kt@gmail.com", true, "Swimming", "sjkandsa", "admin");
        users.add(user);
        when(userService.findByCategory("Swimming")).thenReturn(users);
        List<UserDTO> result = userController.findByCategory("Swimming");
        assertNotNull(result);
    }
    
}
