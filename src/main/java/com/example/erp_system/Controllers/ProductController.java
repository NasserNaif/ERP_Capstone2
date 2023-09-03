package com.example.erp_system.Controllers;

import com.example.erp_system.APIs.ApiResponse;
import com.example.erp_system.Models.Branch;
import com.example.erp_system.Models.Product;
import com.example.erp_system.Services.ProductServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product/")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServices productServices;

    @GetMapping("get")
    public ResponseEntity getAllProducts() {
        return ResponseEntity.status(200).body(productServices.getProducts());
    }

    @PostMapping("add/{branchID}")
    public ResponseEntity addProduct(@PathVariable Integer branchID, @RequestBody @Valid Product newProduct) {
        productServices.addProduct(branchID, newProduct);
        return ResponseEntity.status(201).body(new ApiResponse("product added"));
    }

    @PutMapping("update/{id}")
    public ResponseEntity updateProduct(@PathVariable Integer id, @RequestBody @Valid Product newProduct) {
        productServices.updateProduct(id, newProduct);
        return ResponseEntity.status(201).body(new ApiResponse("product updated"));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable Integer id) {
        productServices.deleteProduct(id);
        return ResponseEntity.status(200).body(new ApiResponse("product deleted"));
    }
}
