package com.example.erp_system.Repos;

import com.example.erp_system.Models.Branch;
import com.example.erp_system.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    Product findProductById(Integer id);

    List<Product> findAllByBranch(Branch branch);
}
