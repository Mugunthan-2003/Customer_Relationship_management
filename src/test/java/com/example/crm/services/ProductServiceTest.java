package com.example.crm.services;

import com.example.crm.entities.Product;
import com.example.crm.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService service;

    private Product product;

    @BeforeEach
    void setup() {
        product = new Product();
        product.setProductId(1L);
        product.setProductName("Biscuits");
        product.setProductCategory("Snacks");
        product.setPrice(10.0);
    }

    @Test
    void testAddProduct() {
        when(repository.save(any(Product.class))).thenReturn(product);
        Product addedProduct = service.addProduct(product);
        assertNotNull(addedProduct);
        assertEquals(product.getProductName(), addedProduct.getProductName());
        verify(repository, times(1)).save(any(Product.class));
    }

    @Test
    void testUpdateProduct() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(product));
        when(repository.save(any(Product.class))).thenReturn(product);
        Product updatedProduct = service.updateProduct(product);
        assertNotNull(updatedProduct);
        assertEquals(product.getProductName(), updatedProduct.getProductName());
        verify(repository, times(1)).findById(anyLong());
        verify(repository, times(1)).save(any(Product.class));
    }

    @Test
    void testDeleteProduct() {
        doNothing().when(repository).deleteById(anyLong());
        service.deleteProduct(1L);
        verify(repository, times(1)).deleteById(anyLong());
    }

    @Test
    void testGetAllProducts() {
        List<Product> products = List.of(product);
        when(repository.findAll()).thenReturn(products);
        List<Product> result = service.getAllProducts();
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetProductById() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(product));
        Product result = service.getProductById(1L);
        assertNotNull(result);
        assertEquals(product.getProductName(), result.getProductName());
        verify(repository, times(1)).findById(anyLong());
    }
}
