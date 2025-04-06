package com.example.crm.services;

import com.example.crm.entities.Product;
import com.example.crm.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product addProduct(Product product) {
        return repository.save(product);
    }

    public Product updateProduct(Product product) {
        Product existingProduct = repository.findById(product.getProductId()).orElse(null);
        if (existingProduct != null) {
            existingProduct.setProductName(product.getProductName());
            existingProduct.setProductCategory(product.getProductCategory());
            existingProduct.setPrice(product.getPrice());
            return repository.save(existingProduct);
        } else {
            return null;
        }
    }

    public void deleteProduct(Long productId) {
        repository.deleteById(productId);
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product getProductById(Long productId) {
        return repository.findById(productId).orElse(null);
    }
}
