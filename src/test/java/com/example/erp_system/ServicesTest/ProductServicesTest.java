package com.example.erp_system.ServicesTest;


import com.example.erp_system.Models.Product;
import com.example.erp_system.Models.User;
import com.example.erp_system.Repos.ProductRepo;
import com.example.erp_system.Services.ProductServices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServicesTest {
    @InjectMocks
    ProductServices productServices;

    @Mock
    ProductRepo productRepo;

    Product product1;
    Product product2;

    List<Product> products;

    @BeforeEach
    void setUp() {
        product1 = new Product(null, "avocado", 200, 55, 19000, null);
        product2 = new Product(null, "avocado", 200, 55, 19000, null);

        products = new ArrayList<>();

        products.add(product1);
        products.add(product2);
    }


    @Test
    public void getProductsTest() {
        when(productRepo.findAll()).thenReturn(products);

        List<Product> productList = productServices.getProducts();

        Assertions.assertEquals(productList, products);

        verify(productRepo, times(1)).findAll();
    }


    @Test
    public void deleteProductTest() {
        when(productRepo.findProductById(product1.getId())).thenReturn(product1);


        productServices.deleteProduct(product1.getId());

        verify(productRepo, times(1)).findProductById(product1.getId());
        verify(productRepo, times(1)).delete(product1);
    }


}
