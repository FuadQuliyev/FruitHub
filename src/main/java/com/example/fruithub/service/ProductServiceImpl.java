package com.example.fruithub.service;

import com.example.fruithub.dto.ProductDto;
import com.example.fruithub.model.*;
import com.example.fruithub.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final QuantityRepository quantityRepository;
    private final CurrencyRepository currencyRepository;
    private final StatusRepository statusRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product addProduct(ProductDto product) {
        Optional<Product> existingProduct = productRepository.findByName(product.getName());
        if (existingProduct.isPresent()){
            return null;
        }
        if (product.getCategoryUuid() == null
                || product.getQuantityUuid() == null
                || product.getCurrencyUuid() == null
                || product.getStatusUuid() == null) {
            throw new RuntimeException("Missing required foreign keys");
        }

        Category category = categoryRepository.findById(product.getCategoryUuid())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Quantity quantity = quantityRepository.findById(product.getQuantityUuid())
                .orElseThrow(() -> new RuntimeException("Quantity not found"));

        Currency currency = currencyRepository.findById(product.getCurrencyUuid())
                .orElseThrow(() -> new RuntimeException("Currency not found"));

        Status status = statusRepository.findById(product.getStatusUuid())
                .orElseThrow(() -> new RuntimeException("Status not found"));

        Product newProduct = Product.builder()
                .name(product.getName())
                .price(product.getPrice())
                .count(product.getCount())
                .category(category)
                .quantity(quantity)
                .currency(currency)
                .status(status)
                .build();

        return  productRepository.save(newProduct);
    }

    @Override
    public void updateProduct(UUID uuid, ProductDto product) {
        Product existingProduct = productRepository.findById(uuid)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Category category = categoryRepository.findById(product.getCategoryUuid())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Quantity quantity = quantityRepository.findById(product.getQuantityUuid())
                .orElseThrow(() -> new RuntimeException("Quantity not found"));

        Currency currency = currencyRepository.findById(product.getCurrencyUuid())
                .orElseThrow(() -> new RuntimeException("Currency not found"));

        Status status = statusRepository.findById(product.getStatusUuid())
                .orElseThrow(() -> new RuntimeException("Status not found"));

        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setCount(product.getCount());
        existingProduct.setCategory(category);
        existingProduct.setQuantity(quantity);
        existingProduct.setCurrency(currency);
        existingProduct.setStatus(status);

        productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(UUID uuid) {
        Product product = productRepository.findById(uuid)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.delete(product);
    }
}
