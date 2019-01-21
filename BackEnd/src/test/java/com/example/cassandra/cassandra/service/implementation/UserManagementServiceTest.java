/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.cassandra.cassandra.service.implementation;

import com.example.cassandra.cassandra.model.User;
import com.example.cassandra.cassandra.repository.UserRepository;
import dto.UserDTO;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.modelmapper.ModelMapper;

/**
 *
 * @author LENOVO
 */
public class UserManagementServiceTest {
    
   private UserManagementService userService;
    private UserRepository userRepository;
    private ModelMapper modMapper;
    
    public UserManagementServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        modMapper = new ModelMapper();
        userService = new UserManagementService(userRepository, modMapper);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAll method, of class CategoryManagementService.
     */
    @Test
    public void userList_Test_filledList(){
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setName("David");
        user.setCategory("BMX");
        user.setEmail("dav@gmail.com");
        user.setPassword("dasodkp");
        user.setActive(true);
        user.setRole("admin");
        when(userRepository.findAll()).thenReturn(Arrays.asList(user));
        
        List<UserDTO> result = userService.getAllUsers();
        assertFalse(result.isEmpty());
        assertEquals(1,result.size());
    }
    
    @Test 
    public void addUserTest(){
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setName("Jonh");
        when(userRepository.save(any(User.class))).thenReturn(user);
        UserDTO result = userService.postUser(modMapper.map(user, UserDTO.class));
        assertNotNull(result);
        assertEquals("Jonh",result.getName());
    }
    
    @Test
    public void deleteUser_Test(){
        User user = new User();
        UUID id = UUID.randomUUID();
        user.setId(id);
        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        UserDTO result = userService.deleteUser(id);
        assertNotNull(result);
        assertEquals(id, result.getId());
    }
    
    @Test
    public void selectUser_Test(){
        UUID id = UUID.randomUUID();
        User user = new User();
        user.setId(id);
        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        UserDTO result = userService.selectUser(id);
        assertNotNull(result);
        assertEquals(id, result.getId());
    }
    
}
