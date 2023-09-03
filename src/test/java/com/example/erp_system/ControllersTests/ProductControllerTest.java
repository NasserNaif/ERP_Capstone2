package com.example.erp_system.ControllersTests;


import com.example.erp_system.Controllers.AdminController;
import com.example.erp_system.Controllers.ProductController;
import com.example.erp_system.Models.Product;
import com.example.erp_system.Models.User;
import com.example.erp_system.Services.ProductServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = ProductController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class ProductControllerTest {
    @MockBean
    ProductServices productServices;

    @Autowired
    MockMvc mockMvc;

    Product product1, product2;

    List<Product> productList;

    @BeforeEach
    void setUp() {
        product1 = new Product(1, "cola", 200, 400, 1200, null);
        product1 = new Product(2, "pepsi", 200, 400, 1200, null);

        productList = new ArrayList<>();

        productList.add(product1);
        productList.add(product2);
    }


    @Test
    public void getAllProductsTest() throws Exception {
        when(productServices.getProducts()).thenReturn(productList);

        mockMvc.perform(get("/api/v1/product/get")).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("pepsi"));
    }


    @Test
    public void deleteProductTest() throws Exception {
        mockMvc.perform(delete("/api/v1/product/delete/{id}", product1.getId()))
                .andExpect(status().isOk());

    }
}
