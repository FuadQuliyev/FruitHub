package com.example.fruithub.controller;


import com.example.fruithub.dto.ProductDto;
import com.example.fruithub.model.Product;
import com.example.fruithub.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getCategory(){
        List<Product> products = productService.getAllProducts();
        if (products == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(products);
    }

    @PostMapping("/add")
    public void addProduct(@RequestBody ProductDto product){
        productService.addProduct(product);
    }

    @PutMapping("/update/{uuid}")
    public void updateProduct(@PathVariable UUID uuid, @RequestBody  ProductDto product){
        productService.updateProduct(uuid, product);
    }

    @DeleteMapping("/delete/{uuid}")
    public void deleteProduct(@PathVariable UUID uuid){
        productService.deleteProduct(uuid);
    }
}
