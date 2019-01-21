/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.cassandra.cassandra.service;

import dto.CategoryDTO;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author LENOVO
 */
public interface CategoryService {
    List<CategoryDTO> getAll();
    CategoryDTO selectCategory(UUID id);
    CategoryDTO addCategory(CategoryDTO category);
    CategoryDTO updateCategory(UUID id, CategoryDTO categoryDTO);
    CategoryDTO deleteCategory(UUID id);
}
