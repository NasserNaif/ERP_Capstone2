package com.example.erp_system.Services;


import com.example.erp_system.APIs.ApiException;
import com.example.erp_system.Models.Branch;
import com.example.erp_system.Models.Employee;
import com.example.erp_system.Models.Product;
import com.example.erp_system.Repos.BranchRepo;
import com.example.erp_system.Repos.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServices {

    private final ProductRepo productRepo;
    private final BranchRepo branchRepo;

    public List<Product> getProducts() {
        return productRepo.findAll();
    }

    public void addProduct(Integer branchID, Product newProduct) {
        Branch branch = branchRepo.findBranchById(branchID);

        if (branch == null) {
            throw new ApiException("wrong branch ID");
        }
        newProduct.setBranch(branch);
        productRepo.save(newProduct);
    }

    public void updateProduct(Integer id, Product newProduct) {
        Product product = productRepo.findProductById(id);

        if (product != null) {
            product.setName(newProduct.getName());
            product.setPrice(newProduct.getPrice());
            product.setSales(newProduct.getSales());
            product.setQuantity(newProduct.getQuantity());
            productRepo.save(product);
        }
        throw new ApiException("wrong ID");
    }

    public void deleteProduct(Integer id) {
        Product product = productRepo.findProductById(id);
        if (product != null)
            productRepo.delete(product);
        else
            throw new ApiException("wrong ID");
    }
}
