/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.cassandra.cassandra.controller;

import com.datastax.driver.core.utils.UUIDs;
import com.example.cassandra.cassandra.model.Category;
import com.example.cassandra.cassandra.repository.CategoryRepository;
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
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;
    
    @GetMapping("/categories")
    public List<Category> getAllCats(){
        return categoryRepository.findAll();
    }
    
    @PostMapping("/categories/create")
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
        category.setId(UUIDs.timeBased());
        Category category1 = categoryRepository.save(category);
        return new ResponseEntity<>(category1, HttpStatus.OK);
    }
    
    @PutMapping("/categories/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") UUID id, @RequestBody Category category){
        Optional<Category> categoryData = categoryRepository.findById(id);
        if(categoryData.isPresent()){
            Category category1 = categoryData.get();
            category1.setName(category.getName());
            
            return new ResponseEntity<>(categoryRepository.save(category1), HttpStatus.OK); 
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("categories/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable UUID id){
        categoryRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
    
}
