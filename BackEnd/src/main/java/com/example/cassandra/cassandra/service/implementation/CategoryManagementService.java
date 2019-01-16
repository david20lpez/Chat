/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.cassandra.cassandra.service.implementation;

import com.datastax.driver.core.utils.UUIDs;
import com.example.cassandra.cassandra.model.Category;
import com.example.cassandra.cassandra.repository.CategoryRepository;
import com.example.cassandra.cassandra.service.CategoryService;
import dto.CategoryDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 *
 * @author LENOVO
 */
@Service
public class CategoryManagementService implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modMapper;
    
    @Override
    public List<CategoryDTO> getAll(){
        List<CategoryDTO> categoriesDTO = new ArrayList<>();
        List<Category> categories = categoryRepository.findAll();
        for(Category cats : categories){
            categoriesDTO.add(modMapper.map(cats, CategoryDTO.class));
        }
        return categoriesDTO;
    }
    
    @Override
    public CategoryDTO addCategory(CategoryDTO categoryDTO){
        categoryDTO.setId(UUIDs.timeBased());
        Category category = categoryRepository.save(modMapper.map(categoryDTO, Category.class));
        return modMapper.map(category, CategoryDTO.class);
    }
    
    @Override
    public CategoryDTO updateCategory(UUID id, CategoryDTO categoryDTO){
        Optional<Category> categoryData = categoryRepository.findById(id);
        Category category = new Category();
        if(categoryData.isPresent()){
            CategoryDTO categoryDTO1 = modMapper.map(categoryData.get(), CategoryDTO.class);
            categoryDTO1.setName(categoryDTO.getName());
            category = categoryRepository.save(modMapper.map(categoryDTO1, Category.class));
        }
        return modMapper.map(category, CategoryDTO.class);
    }
    
    @Override
    public void deleteCategory(UUID id){
        categoryRepository.deleteById(id);
    }
}
