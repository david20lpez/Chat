/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.cassandra.cassandra.controller;

import com.example.cassandra.cassandra.service.implementation.CategoryManagementService;
import dto.CategoryDTO;
import java.util.List;
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
public class CategoryController {

    CategoryManagementService categoryService;
    
    CategoryController(CategoryManagementService categoryService){
        this.categoryService = categoryService;
    }
    
    @GetMapping("/categories")
    public List<CategoryDTO> getAllCats(){
        return categoryService.getAll();
    }
    
    @PostMapping("/categories/create")
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO category){
          CategoryDTO category1 = categoryService.addCategory(category);
        return new ResponseEntity<>(category1, HttpStatus.OK);
    }
    
    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable("id") UUID id, @RequestBody CategoryDTO categoryDTO){
          CategoryDTO category = categoryService.updateCategory(id, categoryDTO);
          return new ResponseEntity<>(category, HttpStatus.OK);
    }
    
    @DeleteMapping("categories/{id}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable UUID id){
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
    
}
