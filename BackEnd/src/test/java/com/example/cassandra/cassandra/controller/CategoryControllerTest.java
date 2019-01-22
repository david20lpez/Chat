/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.cassandra.cassandra.controller;

import com.example.cassandra.cassandra.service.implementation.CategoryManagementService;
import dto.CategoryDTO;
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
public class CategoryControllerTest {
    
    private CategoryManagementService categoryService;
    private CategoryController categoryController;
    
    public CategoryControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        categoryService = Mockito.mock(CategoryManagementService.class);
        categoryController = new CategoryController(categoryService);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void getCats_Test(){
        List<CategoryDTO> categories = new ArrayList<>();
        CategoryDTO category = new CategoryDTO(UUID.randomUUID(), "Lifestyle");
        categories.add(category);
        when(categoryService.getAll()).thenReturn(categories);
        List<CategoryDTO> result = categoryController.getAllCats();
        assertNotNull(result);
    }
    
    @Test
    public void addCategory_Test(){
        CategoryDTO category = new CategoryDTO(UUID.randomUUID(), "Cuisine");
        when(categoryService.addCategory(category)).thenReturn(category);
        ResponseEntity <?> result = categoryController.addCategory(category);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
 
}
