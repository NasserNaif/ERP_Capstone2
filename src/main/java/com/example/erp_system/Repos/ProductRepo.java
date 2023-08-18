package com.example.erp_system.Repos;

import com.example.erp_system.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    Product findProductById(Integer id);
}
