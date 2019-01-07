/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.cassandra.cassandra.repository;

import com.example.cassandra.cassandra.model.Category;
import java.util.UUID;
import org.springframework.data.cassandra.repository.CassandraRepository;

/**
 *
 * @author LENOVO
 */
public interface CategoryRepository extends CassandraRepository<Category, UUID>{
}
