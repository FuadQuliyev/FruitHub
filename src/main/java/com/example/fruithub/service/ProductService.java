package com.example.fruithub.service;

import com.example.fruithub.dto.ProductDto;
import com.example.fruithub.model.Product;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<Product> getAllProducts();
    Product addProduct(ProductDto product);
    void updateProduct(UUID uuid, ProductDto product);
    void deleteProduct(UUID uuid);
}
