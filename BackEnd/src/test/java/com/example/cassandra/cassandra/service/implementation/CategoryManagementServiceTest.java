/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.cassandra.cassandra.service.implementation;

import com.example.cassandra.cassandra.model.Category;
import com.example.cassandra.cassandra.repository.CategoryRepository;
import dto.CategoryDTO;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.modelmapper.ModelMapper;

/**
 *
 * @author LENOVO
 */
public class CategoryManagementServiceTest {
    
    private CategoryManagementService categoryService;
    private CategoryRepository categoryRepository;
    private ModelMapper modMapper;
    
    public CategoryManagementServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        categoryRepository = Mockito.mock(CategoryRepository.class);
        modMapper = new ModelMapper();
        categoryService = new CategoryManagementService(categoryRepository, modMapper);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAll method, of class CategoryManagementService.
     */
    @Test
    public void categoryList_Test_filledList(){
        Category category = new Category();
        category.setId(UUID.randomUUID());
        category.setName("Swimming");
        when(categoryRepository.findAll()).thenReturn(Arrays.asList(category));
        
        List<CategoryDTO> result = categoryService.getAll();
        assertFalse(result.isEmpty());
        assertEquals(1,result.size());
    }
    
    @Test 
    public void addCategoryTest(){
        Category category = new Category();
        category.setId(UUID.randomUUID());
        category.setName("Education");
        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        CategoryDTO result = categoryService.addCategory(modMapper.map(category, CategoryDTO.class));
        assertNotNull(result);
        assertEquals("Education",result.getName());
    }
    
    @Test
    public void updateCategoryTest(){
        Category category = new Category(UUID.randomUUID(),"BMX");
        Category category1 = new Category();
        category1.setName("Tourism");
        when(categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));
        CategoryDTO result = categoryService.updateCategory(category.getId(), modMapper.map(category1, CategoryDTO.class));
        assertEquals("Tourism",category.getName());
    }
}
