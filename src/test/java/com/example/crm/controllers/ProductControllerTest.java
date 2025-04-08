package com.example.crm.controllers;

import com.example.crm.entities.Product;
import com.example.crm.services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    private ProductService service;

    @InjectMocks
    private ProductController controller;

    private MockMvc mockMvc;

    private Product product;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        product = new Product();
        product.setProductId(1L);
        product.setProductName("Biscuits");
        product.setProductCategory("Snacks");
        product.setPrice(10.0);
    }

    @Test
    void testAddProduct() throws Exception {
        when(service.addProduct(any(Product.class))).thenReturn(product);
        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(product)))
                .andExpect(status().isOk());
        verify(service, times(1)).addProduct(any(Product.class));
    }

    @Test
    void testUpdateProduct() throws Exception {
        when(service.updateProduct(any(Product.class))).thenReturn(product);
        mockMvc.perform(put("/api/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(product)))
                .andExpect(status().isOk());
        verify(service, times(1)).updateProduct(any(Product.class));
    }

    @Test
    void testDeleteProduct() throws Exception {
        mockMvc.perform(delete("/api/products/1"))
                .andExpect(status().isOk());
        verify(service, times(1)).deleteProduct(anyLong());
    }

    @Test
    void testGetAllProducts() throws Exception {
        List<Product> products = List.of(product);
        when(service.getAllProducts()).thenReturn(products);
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk());
        verify(service, times(1)).getAllProducts();
    }

    @Test
    void testGetProductById() throws Exception {
        when(service.getProductById(anyLong())).thenReturn(product);
        mockMvc.perform(get("/api/products/1"))
                .andExpect(status().isOk());
        verify(service, times(1)).getProductById(anyLong());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
