package com.example.fruithub.service;

import com.example.fruithub.dto.CategoryDto;
import com.example.fruithub.model.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    ResponseEntity<Category> addCategory(CategoryDto category);
    List<Category> getAllCategory();
    void updateCategory(UUID uuid, CategoryDto category);
    void deleteCategory(UUID uuid);
}
