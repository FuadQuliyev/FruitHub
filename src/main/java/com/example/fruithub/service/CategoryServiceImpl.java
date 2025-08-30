package com.example.fruithub.service;

import com.example.fruithub.dto.CategoryDto;
import com.example.fruithub.model.Category;
import com.example.fruithub.model.Status;
import com.example.fruithub.repository.CategoryRepository;
import com.example.fruithub.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final StatusRepository statusRepository;

    @Override
    public ResponseEntity<Category> addCategory(CategoryDto category) {
        Optional<Category> existingCategory = categoryRepository.findByName(category.getName());
        if (existingCategory.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }else{
            Category parentCategory = null;
            if (category.getCategoryUuid() != null) {
                parentCategory = categoryRepository.findById(category.getCategoryUuid()).orElse(null);
            }
            Status status = statusRepository.findById(category.getStatusUuid())
                    .orElseThrow(() -> new RuntimeException("Status not found"));
            Category newCategory = Category.builder()
                    .name(category.getName())
                    .description(category.getDescription())
                    .category(parentCategory)
                    .status(status)
                    .build();
            Category savedCategory = categoryRepository.save(newCategory);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
        }
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public void updateCategory(UUID uuid, CategoryDto category) {
        Category existingCategory = categoryRepository.findById(uuid)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Category parentCategory = null;
        if (category.getCategoryUuid() != null) {
            parentCategory = categoryRepository.findById(category.getCategoryUuid()).orElse(null);
        }

        Status status = statusRepository.findById(category.getStatusUuid())
                        .orElseThrow(() -> new RuntimeException("Status not found"));

        existingCategory.setName(category.getName());
        existingCategory.setDescription(category.getDescription());
        existingCategory.setCategory(parentCategory);
        existingCategory.setStatus(status);
        categoryRepository.save(existingCategory);

    }

    @Override
    public void deleteCategory(UUID uuid) {
        Category category = categoryRepository.findById(uuid)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        categoryRepository.delete(category);
    }
}
